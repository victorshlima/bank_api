package br.com.dbtest.bank.dao;

import br.com.dbtest.bank.domain.Lancamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class LancamentoDaoImpl implements LancamentoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private LancamentoDao lancamentoDao;

    @Override
    public Lancamento findLancamento(Long id) {
        return entityManager
                .createQuery("select l from Lancamento l where id =" + id, Lancamento.class)
                .getSingleResult();
    }

    @Override
    public void save(Lancamento lancamento) {

        entityManager.persist(lancamento);
    }

    @Override
    public List<Lancamento> findAll() {
        return entityManager
                .createQuery("select l from Lancamento l", Lancamento.class)
                .getResultList();
    }
}