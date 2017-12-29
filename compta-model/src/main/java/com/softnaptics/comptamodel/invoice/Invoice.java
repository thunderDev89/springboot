package com.softnaptics.comptamodel.invoice;

import com.softnaptics.comptamodel.entries.AbstractEntry;

import javax.persistence.*;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Month month;

    //@OneToMany(mappedBy = "invoice")
    @Transient
    private List<AbstractEntry> entries;

    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date billingDate;

    public Invoice() {
    }

    public Invoice(String name, Month month, Date billingDate) {
        this.name = name;
        this.month = month;
        this.billingDate = billingDate;
        entries = new ArrayList<>(0);
    }

    public void addEntry(AbstractEntry entry) {
        entries.add(entry);
    }

    public List<AbstractEntry> getEntries() {
        return entries;
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

    @Override
    public String toString() {
        return "Invoice{" +
                "name='" + name + '\'' +
                ", month=" + month +
                ", billingDate=" + billingDate +
                '}';
    }
}
