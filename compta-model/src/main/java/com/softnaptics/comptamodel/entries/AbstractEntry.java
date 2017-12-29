package com.softnaptics.comptamodel.entries;

import com.softnaptics.comptamodel.invoice.Invoice;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntry implements Entry {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private double tva;

    @Column(name = "unit_price_ht")
    private double unitPriceHT;

    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    //@Temporal(TemporalType.DATE)
    private Date pricingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
