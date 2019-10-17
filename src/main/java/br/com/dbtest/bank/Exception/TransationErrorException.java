package br.com.dbtest.bank.Exception;


public class TransationErrorException extends RuntimeException {

    public TransationErrorException(String message) {
        super(message);
    }
}
