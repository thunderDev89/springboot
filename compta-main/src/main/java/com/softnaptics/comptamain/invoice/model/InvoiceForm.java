package com.softnaptics.comptamain.invoice.model;

import com.softnaptics.comptamodel.invoice.Invoice;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Month;
import java.util.Date;

public class InvoiceForm {

    private Integer id;
    private String name;
    private Month month;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date billingDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public static InvoiceForm from(Invoice invoice) {
        final InvoiceForm invoiceForm = new InvoiceForm();
        invoiceForm.setId(invoice.getId());
        invoiceForm.setName(invoice.getName());
        invoiceForm.setMonth(invoice.getMonth());
        invoiceForm.setBillingDate(invoice.getBillingDate());

        return invoiceForm;
    }

    public static Invoice to(InvoiceForm invoiceForm) {
        final Invoice invoice = new Invoice();
        invoice.setId(invoiceForm.getId());
        invoice.setName(invoiceForm.getName());
        invoice.setMonth(invoiceForm.getMonth());
        invoice.setBillingDate(invoiceForm.getBillingDate());

        return invoice;
    }
}
