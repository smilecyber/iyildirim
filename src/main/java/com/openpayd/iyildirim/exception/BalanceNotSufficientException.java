package com.openpayd.iyildirim.exception;

public class BalanceNotSufficientException extends RuntimeException{
    public BalanceNotSufficientException(String message){
        super(message);
    }
}
