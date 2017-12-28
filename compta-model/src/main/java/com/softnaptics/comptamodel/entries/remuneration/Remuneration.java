package com.softnaptics.comptamodel.entries.remuneration;

import com.softnaptics.comptamodel.entries.AbstractEntry;
import com.softnaptics.comptamodel.entries.EntryType;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Remuneration extends AbstractEntry {

    public Remuneration(double amountHT, Date pricingDate) {
        super("Remuneration", 0, amountHT, pricingDate);
    }

    @Override
    public double getQty() {
        return 1;
    }

    @Override
    public EntryType getEntryType() {
        return EntryType.REMUNERATION;
    }

    /*@Override
    public String getName() {
        return "Remuneration";
    }

    @Override
    public double getTVA() {
        return 0;
    }

    @Override
    public double getAmountHT() {
        return amount;
    }

    @Override
    public double getQty() {
        return 1;
    }

    @Override
    public EntryType getEntryType() {
        return EntryType.REMUNERATION;
    }

    @Override
    public Date getDate() {
        return billingDate;
    }*/
}
