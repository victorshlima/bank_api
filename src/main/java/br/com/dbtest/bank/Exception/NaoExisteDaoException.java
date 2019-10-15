package br.com.dbtest.bank.Exception;




public class NaoExisteDaoException extends RuntimeException {


    public NaoExisteDaoException(String message) {
        super(message);
    }
}
