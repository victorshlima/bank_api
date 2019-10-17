package br.com.dbtest.bank.Exception;


public class LimitOverException extends RuntimeException {

    public LimitOverException(String message) {
        super(message);
    }
}
