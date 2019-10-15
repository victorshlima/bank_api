package br.com.dbtest.bank.service;

import br.com.dbtest.bank.Exception.LimitOverException;
import br.com.dbtest.bank.Exception.TransationErrorException;
import br.com.dbtest.bank.dao.ContaDao;
import br.com.dbtest.bank.dao.LancamentoDao;
import br.com.dbtest.bank.domain.ContaCorrente;
import br.com.dbtest.bank.domain.Lancamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class lancamentoServiceImpl implements LancamentoService  {
    @Autowired
    private ContaDao contaDao;

    @Autowired
    private LancamentoDao lancamentoDao;

    @Override
    public Lancamento lancamento(Lancamento lancamento) {

        validaAgenciaConta(lancamento.getAgenciaOrig(), lancamento.getContaOrig());
        validaAgenciaConta(lancamento.getAgenciaDest(), lancamento.getContaDest());
        veficaSaldo(contaDao.findAccount(lancamento.getAgenciaOrig(), lancamento.getContaOrig()), lancamento.getValor());
        return execTrans(lancamento);
    }

    private boolean validaAgenciaConta (int agencia, int conta){
       if ( contaDao.findAccount(agencia,conta ) !=null)   {
           System.out.println( "validaAgenciaConta = true"+ "\n");
           return true;
       }    else {
           System.out.println( "validaAgenciaConta = false"+ "\n");
           return false;
       }
    }

    private void veficaSaldo(ContaCorrente conta, Double valorLancamento) {
        if ( conta.getSaldo() + conta.getLimite() >= valorLancamento ){
            System.out.println( "possi Possui Saldo" + "\n");

        } else {
            System.out.println("Nao Possui Saldo" + "\n");
            throw new LimitOverException("Não Possui Saldo Sificiente");
        }
    }

    private Lancamento execTrans(Lancamento lancamento) {
        try {
            contaDao.tranfere(contaDao.findAccount(lancamento.getAgenciaOrig(), lancamento.getContaOrig()), -lancamento.getLimite());
            contaDao.tranfere(contaDao.findAccount(lancamento.getAgenciaDest(), lancamento.getContaDest()), +lancamento.getLimite());
            lancamentoDao.save(lancamento);
            Lancamento l = lancamentoDao.findLancamento(lancamento.getId());
            return l;
        } catch (EntityNotFoundException ex) {
            throw new TransationErrorException("Erro ao executar a Transferencia");
        }

    }


}