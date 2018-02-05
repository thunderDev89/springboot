package com.softnaptics.comptamodel.entries.activity;

import com.softnaptics.comptamodel.entries.AbstractEntry;
import com.softnaptics.comptamodel.entries.EntryType;
import com.softnaptics.comptamodel.entries.activity.property.ActivityPeriod;
import com.softnaptics.comptamodel.entries.activity.property.ActivityType;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Activity extends AbstractEntry {

    @Enumerated(EnumType.STRING)
    private ActivityType type;

    @Embedded
    private ActivityPeriod period;

    private Activity() {
    }

    public Activity(String name, double tva, double amountHT, ActivityType type, ActivityPeriod period, Date pricingDate) {
        super(name, tva, amountHT, pricingDate);
        this.type = type;
        this.period = period;
    }

    public ActivityType getType() {
        return type;
    }

    public ActivityPeriod getPeriod() {
        return period;
    }

    public void setPeriod(ActivityPeriod period) {
        this.period = period;
    }

    @Override
    public double getQty() {
        return period.getCountInDayType() * type.getRate();
    }

    @Override
    public EntryType getEntryType() {
        return EntryType.ACTIVITY;
    }
}
