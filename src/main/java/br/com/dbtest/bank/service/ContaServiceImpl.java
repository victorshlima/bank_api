package br.com.dbtest.bank.service;

import br.com.dbtest.bank.dao.ContaDao;
import br.com.dbtest.bank.domain.ContaCorrente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaDao contaDao;

    @Override
    public void save(ContaCorrente conta) {
        contaDao.save(conta);
    }

    @Override @Transactional(readOnly = true)
    public List<ContaCorrente> findAll() {
        return contaDao.findAll();
    }

}