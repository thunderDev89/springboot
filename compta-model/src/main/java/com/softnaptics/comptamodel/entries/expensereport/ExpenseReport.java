package com.softnaptics.comptamodel.entries.expensereport;

import com.softnaptics.comptamodel.entries.Entry;
import com.softnaptics.comptamodel.entries.EntryType;

import java.util.Date;

/**
 * Represents "Note de frais"
 */
public class ExpenseReport implements Entry {

    private String name;
    private double amount;
    private double tva;
    private Date billingDate;

    public ExpenseReport() {
    }

    public ExpenseReport(String name, double amount, double tva, Date billingDate) {
        this.name = name;
        this.amount = amount;
        this.tva = tva;
        this.billingDate = billingDate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getTVA() {
        return tva;
    }

    @Override
    public double getUnitPriceHT() {
        return amount;
    }

    @Override
    public double getQty() {
        return 1;
    }

    @Override
    public EntryType getEntryType() {
        return EntryType.EXPENSE_REPORT;
    }

    @Override
    public Date getDate() {
        return billingDate;
    }
}
