package br.com.dbtest.bank.service;

import br.com.dbtest.bank.domain.ContaCorrente;

import java.util.List;

public interface ContaService {

    void save(ContaCorrente conta);

    List<ContaCorrente> findAll();

}