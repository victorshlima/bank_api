package br.com.dbtest.bank.service;

import br.com.dbtest.bank.dao.ContaDao;
import br.com.dbtest.bank.dao.LancamentoDao;
import br.com.dbtest.bank.domain.ContaCorrente;
import br.com.dbtest.bank.domain.Lancamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class lancamentoServiceImpl implements LancamentoService  {
    @Autowired
    private ContaDao contaDao;

    @Autowired
    private LancamentoDao lancamentoDao;

    @Override
    public Lancamento lancamento(Lancamento lancamento) {

        System.out.println( "Etapa 1"+ "\n");
         validaAgenciaConta( lancamento.getAgenciaOrig(), lancamento.getContaOrig() );
         validaAgenciaConta( lancamento.getAgenciaDest(), lancamento.getContaDest() );

    if (  veficaSaldo(contaDao.findAccount(lancamento.getAgenciaOrig(),lancamento.getContaOrig()  ) , lancamento.getValor()) ){
        System.out.println( "veficaSaldo"+ "\n");
        System.out.println( "possui Possui Saldo"+ "\n");
        System.out.println( contaDao.findAccount(lancamento.getAgenciaOrig(), lancamento.getContaOrig() ).getSaldo());
    }  else {
        System.out.println( "veficaSaldo"+ "\n");
        System.out.println( "Nao Possui Saldo"+ "\n");
        System.out.println( contaDao.findAccount(lancamento.getAgenciaOrig(), lancamento.getContaOrig() ).getSaldo());
    }

        System.out.println( "Etapa 2"+ "\n");
    if ( contaDao.tranfere(contaDao.findAccount(lancamento.getAgenciaOrig(),lancamento.getContaOrig()  ),  -lancamento.getLimite())){

        System.out.println( "tranfere - contaDaoOrig"+ "\n");
    }else {
        System.out.println( "ERRO tranfere"+ "\n");
    }
        System.out.println( "Etapa 3"+ "\n");
   if ( contaDao.tranfere(contaDao.findAccount(lancamento.getAgenciaDest(),lancamento.getContaDest()  ),  +lancamento.getLimite())){

            System.out.println( "tranfere - contaDaoOrig"+ "\n");
   }else {
            System.out.println( "ERRO tranfere"+ "\n");
   }

 //  TESTE
        lancamento.setStatus("RECUSADA");
   // TESTE
        lancamento.setStatus("EFETUADA");
        lancamentoDao.save(lancamento);
        Lancamento l = lancamentoDao.findLancamento(lancamento.getId());
        System.out.println(l.toString() + " Persistencia");



        System.out.println( l.toString());
    return l;
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

    private boolean veficaSaldo (ContaCorrente conta, Double valorLancamento){
        if ( conta.getSaldo() + conta.getLimite() >= valorLancamento ){
            System.out.println( "possi Possui Saldo" + "\n");
            return  true;
        }
        System.out.println( "Nao Possui Saldo"+ "\n");
        return  false;
    }
}