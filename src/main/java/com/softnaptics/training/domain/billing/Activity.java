package com.softnaptics.training.domain.billing;

import com.softnaptics.training.domain.billing.utils.ActivityType;
import com.softnaptics.training.domain.billing.utils.Period;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Activity {
    private static final double TJM = 330;
    private static final double TVA = .2;

    @Id
    @GeneratedValue
    private Long id;

    private final String name;

    @Enumerated(EnumType.STRING)
    private final ActivityType type;

    @Column(columnDefinition = "TIMESTAMP")
    private final Date accomplishedDate;

    @Embedded
    private final Period period;

    @ManyToOne
    private Invoice invoice;

    public Activity(String name, ActivityType type, Date accomplishedDate, Period period) {
        this.name = name;
        this.type = type;
        this.accomplishedDate = accomplishedDate;
        this.period = period;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ActivityType getType() {
        return type;
    }

    public Date getAccomplishedDate() {
        return accomplishedDate;
    }

    public Period getPeriod() {
        return period;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public double getAmountHT() {
        return period.getCountToDayType() * type.getRate().doubleValue() * TJM;
    }

    public double getAmountTTC() {
        return getAmountHT() * (1 + TVA);
    }
}
