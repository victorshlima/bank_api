package br.com.dbtest.bank.dao;


import br.com.dbtest.bank.domain.ContaCorrente;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaDao {

    List<ContaCorrente> findAll() ;

    ContaCorrente findAccount(int agencia, int conta) ;

    void save (ContaCorrente conta);

    boolean tranfere(ContaCorrente conta, Double valor);

}
