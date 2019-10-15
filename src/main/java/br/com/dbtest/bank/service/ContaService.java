package br.com.dbtest.bank.service;

import br.com.dbtest.bank.domain.ContaCorrente;

import java.util.List;

public interface ContaService {





    void save(ContaCorrente conta);

    ContaCorrente findAccount(int agencia, int conta);

    ContaCorrente transferencia(int agencia, int conta, double valor);

    List<ContaCorrente> findAll();

    ContaCorrente lancamento(int agenciaOrig, int contaOrig,
                             int agenciaDest, int contaDest,
                             double valor, String  tipo );

    double getSaldo (int agencia, int conta);

}