package com.mrizkisaputra.exceptions;

public class PaymentExceedInvoiceAmountException extends Exception {
    public PaymentExceedInvoiceAmountException(String message) {
        super(message);
    }
}
