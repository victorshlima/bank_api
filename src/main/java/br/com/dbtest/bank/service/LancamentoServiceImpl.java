package br.com.dbtest.bank.service;

import br.com.dbtest.bank.Exception.LimitOverException;
import br.com.dbtest.bank.Exception.TransationErrorException;
import br.com.dbtest.bank.dao.ContaDao;
import br.com.dbtest.bank.dao.LancamentoDao;
import br.com.dbtest.bank.domain.ContaCorrente;
import br.com.dbtest.bank.domain.Lancamento;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class LancamentoServiceImpl implements LancamentoService {

    static final Logger logger = LogManager.getLogger(LancamentoServiceImpl.class.getName());

    @Autowired
    private ContaDao contaDao;

    @Autowired
    private LancamentoDao lancamentoDao;

    @Override
    public Lancamento lancamento(Lancamento lancamento) {

        validateAccount(lancamento.getAgenciaOrig(), lancamento.getContaOrig());
        validateAccount(lancamento.getAgenciaDest(), lancamento.getContaDest());
        veficaSaldo(contaDao.findAccount(lancamento.getAgenciaOrig(), lancamento.getContaOrig()), lancamento.getValor());


        return execTrans(lancamento);
    }

    private boolean validateAccount(int agency, int account) {
        if (contaDao.findAccount(agency, account) != null) {
            logger.debug("Validate account true");
           return true;
       }    else {
           return false;
       }
    }

    private void veficaSaldo(ContaCorrente account, Double valuePosting) {
        if (account.getSaldo() + account.getLimite() >= valuePosting) {

        } else {
            throw new LimitOverException("Don't have maney enough");
        }
    }

    private Lancamento execTrans(Lancamento lancamento) {
        try {
            contaDao.tranfere(contaDao.findAccount(lancamento.getAgenciaOrig(), lancamento.getContaOrig()), -lancamento.getValor());
            contaDao.tranfere(contaDao.findAccount(lancamento.getAgenciaDest(), lancamento.getContaDest()), +lancamento.getValor());
            lancamentoDao.save(lancamento);
            Lancamento l = lancamentoDao.findLancamento(lancamento.getId());
            return l;
        } catch (EntityNotFoundException ex) {
            throw new TransationErrorException("Transf execute error");
        }

    }


}