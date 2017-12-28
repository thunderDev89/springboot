package com.softnaptics.comptamodel.entries;

import java.util.Date;

public interface Entry {

    String getName();

    double getTVA();

    double getUnitPriceHT();

    double getQty();

    EntryType getEntryType();

    Date getDate();
}
