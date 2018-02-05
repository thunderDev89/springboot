package com.softnaptics.comptamodel.entries.charges;

import com.softnaptics.comptamodel.entries.AbstractEntry;
import com.softnaptics.comptamodel.entries.EntryType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Entity
public class Charges extends AbstractEntry {

    @Enumerated(EnumType.STRING)
    private EntryType entryType;

    private Charges() {
    }

    Charges(String name, double tva, double amountHT, Date pricingDate, EntryType entryType) {
        super(name, tva, amountHT, pricingDate);
        this.entryType = entryType;
    }

    @Override
    public double getQty() {
        return 1;
    }

    @Override
    public EntryType getEntryType() {
        return entryType;
    }
}
