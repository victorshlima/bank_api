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
    public ContaCorrente findAccount(int agencia, int conta) {
        return contaDao.findAccount(agencia,conta );
    }

    @Override
    //  @Transactional(readOnly = false)
    public ContaCorrente lancamento(int agenciaOrig, int contaOrig,
                                    int agenciaDest, int contaDest,
                                    double valor, String  tipo )
    {
        ContaCorrente c   = contaDao.findAccount(agenciaOrig,contaOrig );
        // Verifica Saldo
        if (  validaAgenciaConta( agenciaOrig, contaOrig ) != null ){
            System.out.println( "validaAgenciaConta"+ "\n");
            System.out.println( validaAgenciaConta( agenciaOrig, contaOrig ).toString());
        }else {
            System.out.println( "Nao existe conta " + agenciaOrig + contaOrig+ "\n");
        }

        if (  validaAgenciaConta( agenciaDest, contaDest ) != null ){
            System.out.println( "validaAgenciaConta"+ "\n");
            System.out.println( validaAgenciaConta( agenciaDest, agenciaDest ).toString());
        } else {
            System.out.println( "Nao existe conta " + agenciaDest + agenciaDest+ "\n");

        }
        // Validar o saldo + o LIMITE
        if (  veficaSaldo(contaDao.findAccount(agenciaOrig,contaOrig ) , valor ) ){
            System.out.println( "veficaSaldo"+ "\n");
            System.out.println( "possui Possui Saldo"+ "\n");
            System.out.println( contaDao.findAccount(agenciaOrig, contaOrig ).getSaldo());
        }  else {
            System.out.println( "veficaSaldo"+ "\n");
            System.out.println( "Nao Possui Saldo"+ "\n");
        }  System.out.println( contaDao.findAccount(agenciaOrig, contaOrig ).getSaldo());
        if ( contaDao.tranfere(contaDao.findAccount(agenciaOrig,contaOrig ),  -valor)){

            System.out.println( "tranfere - contaDaoOrig"+ "\n");
        }else {
            System.out.println( "ERRO tranfere"+ "\n");
        }
        if ( contaDao.tranfere(contaDao.findAccount(agenciaDest,contaDest ),  valor)){

            System.out.println( "tranfere - contaDaoDest"+ "\n");
        }else {
            System.out.println( "ERRO tranfere"+ "\n");
        }
        return c;
    }



    @Override @Transactional(readOnly = true)
    public List<ContaCorrente> findAll() {
        return contaDao.findAll();
    }




    private ContaCorrente validaAgenciaConta (int agencia, int conta){
        return   contaDao.findAccount(agencia,conta );
    }

    private boolean veficaSaldo (ContaCorrente conta, Double valorLancamento){
        if ( conta.getSaldo() >= valorLancamento ){
            System.out.println( "possi Possui Saldo" + "\n");
            return  true;
        }
        System.out.println( "Nao Possui Saldo"+ "\n");
        return  false;
    }

    @Override
    public double getSaldo(int agencia, int conta) {
        contaDao.saldo(agencia, conta); //Ao ser localizado o Curso curso Objeto muda para estado persistente
        return contaDao.saldo(agencia, conta);
    }

    @Override
    public ContaCorrente transferencia(int agencia, int conta, double valor) {
        return null;
    }
}