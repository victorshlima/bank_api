package br.com.dbtest.bank.dao;

import br.com.dbtest.bank.domain.ContaCorrente;
import br.com.dbtest.bank.domain.Lancamento;
import br.com.dbtest.bank.service.LancamentoServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class ContaDaoImpl implements ContaDao {
    static final Logger logger = LogManager.getLogger(LancamentoServiceImpl.class.getName());
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ContaDao contaDao;


    @Override
    public boolean tranfere(ContaCorrente conta, Double valor, Lancamento lanc) {

        ContaCorrente c = contaDao.findAccount(conta.getAgencia(), conta.getConta());
        try {
            c.setSaldo(c.getSaldo() + valor);
            c.setLancamento(lanc);
            entityManager.merge(c);
            logger.debug("Transf Sucess");
        }catch (Exception e){
            new ExceptionInInitializerError("Transf Error");
            return false;
        }
        return true;
    }

    @Override
    public List<ContaCorrente> findAll() {
        return entityManager
                .createQuery("select c from ContaCorrente c", ContaCorrente.class)
                .getResultList();
    }

    @Override
    public ContaCorrente findAccount(int agencia, int conta) {
        return entityManager
                .createQuery("select c from ContaCorrente c where agencia =" + agencia + " and conta =" + conta, ContaCorrente.class)
                .getSingleResult();
    }

    @Override
    public void save(ContaCorrente conta) {
        entityManager.persist(conta);
    }


}