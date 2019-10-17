package br.com.dbtest.bank.Exception;


public class NotExistDaoException extends RuntimeException {

    public NotExistDaoException(String message) {
        super(message);
    }
}
