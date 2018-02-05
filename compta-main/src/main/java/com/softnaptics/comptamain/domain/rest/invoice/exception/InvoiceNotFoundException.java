package com.softnaptics.comptamain.domain.rest.invoice.exception;

public class InvoiceNotFoundException extends RuntimeException {

    public InvoiceNotFoundException(String msg) {
        super(msg);
    }
}
