package com.epam.springmvc.exception;

public class NotEnoughFundsException extends Exception {

    public NotEnoughFundsException(int balance) {
        super("NotEnoughFundsException for the requested operation, balance =" + balance);
    }
}
