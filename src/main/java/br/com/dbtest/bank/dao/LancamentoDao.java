package br.com.dbtest.bank.dao;


import br.com.dbtest.bank.domain.Lancamento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LancamentoDao {

    Lancamento findLancamento(Long id) ;

    void save(Lancamento lancamento);

    List<Lancamento> findAll();

}