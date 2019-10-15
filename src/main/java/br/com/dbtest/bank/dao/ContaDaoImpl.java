package br.com.dbtest.bank.dao;

import br.com.dbtest.bank.domain.ContaCorrente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class ContaDaoImpl implements ContaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ContaDao contaDao;

    @Override
    public boolean tranfere(ContaCorrente conta, Double valor) {

        ContaCorrente conta1 = contaDao.findAccount(conta.getAgencia(),conta.getConta());
        try {
            conta1.setSaldo(   conta1.getSaldo() + valor);
            entityManager.merge(conta1);
            System.out.println( conta1.toString());
            System.out.println( "Sucesso na Traferencia"+ "\n");
        }catch (Exception e){
            System.out.println( conta1.toString());
            System.out.println( "Erro ao transferir"+ "\n");
            return false;
        }
        return true;
    }

    @Override
    public double saldo(int agencia, int conta) {
        //  throw new NaoExisteDaoException("ContaCorrente" + agencia + conta +" não encontrada" );
        return 000.00;
    }



    @Override
    public ContaCorrente getConta() {
        return entityManager
                .createQuery("select c from ContaCorrente c", ContaCorrente.class)
                .getSingleResult();
    }

    @Override
    public List<ContaCorrente> findAll() {
        return entityManager
                .createQuery("select c from ContaCorrente c", ContaCorrente.class)
                // .createQuery("select c from ContaCorrente c where agencia =1000 and conta =1", ContaCorrente.class)
                .getResultList();
    }

    @Override
    public ContaCorrente findAccount(int agencia, int conta) {
        return entityManager
                .createQuery("select c from ContaCorrente c where agencia =" + agencia + " and conta =" + conta, ContaCorrente.class)
                //   .createQuery("select c from ContaCorrente c where agencia =1000 and conta =1", ContaCorrente.class)
                .getSingleResult();
    }

    @Override
    public void save(ContaCorrente conta) {
        entityManager.persist(conta);
    }


}