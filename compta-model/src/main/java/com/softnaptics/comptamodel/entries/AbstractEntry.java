package com.softnaptics.comptamodel.entries;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntry implements Entry {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private double tva;

    private double unitPriceHT;

    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    //@Temporal(TemporalType.DATE)
    private Date pricingDate;

    public AbstractEntry() {
    }

    public AbstractEntry(String name, double tva, double amountHT, Date pricingDate) {
        this.name = name;
        this.tva = tva;
        this.unitPriceHT = amountHT;
        this.pricingDate = pricingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return unitPriceHT;
    }

    @Override
    public Date getDate() {
        return pricingDate;
    }
}
