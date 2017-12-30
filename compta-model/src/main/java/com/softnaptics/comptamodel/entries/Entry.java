package com.softnaptics.comptamodel.entries;

import java.util.Date;

interface Entry {

    String getName();

    double getTVA();

    double getUnitPriceHT();

    double getQty();

    EntryType getEntryType();

    Date getDate();
}
