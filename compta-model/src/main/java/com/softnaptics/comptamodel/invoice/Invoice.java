package com.softnaptics.comptamodel.invoice;

import com.softnaptics.comptamodel.entries.AbstractEntry;
import com.softnaptics.comptamodel.entries.activity.Activity;
import com.softnaptics.comptamodel.entries.charges.Charges;

import javax.persistence.*;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private Month month;

    //TODO Replace all these collections (List) by Set to improve performances
    @Transient
    private List<AbstractEntry> entries;

    @OneToMany(
            mappedBy = "invoice",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Activity> activities;

    @OneToMany(
            mappedBy = "invoice",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Charges> charges;

    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date billingDate;

    public Invoice() {
    }

    public Invoice(String name, Month month, Date billingDate) {
        this.name = name;
        this.month = month;
        this.billingDate = billingDate;
        entries = new ArrayList<>(0);
        activities = new ArrayList<>(0);
        charges = new ArrayList<>(0);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public void addActivity(Activity activity) {
        activities.add(activity);
        activity.setInvoice(this);
        //addEntry(activity);
    }

    public void addCharge(Charges charge) {
        charges.add(charge);
        charge.setInvoice(this);
    }

    public List<AbstractEntry> getEntries() {
        final List<AbstractEntry> entries = new ArrayList<>(activities.size() + charges.size());
        entries.addAll(activities);
        entries.addAll(charges);
        return entries;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Charges> getCharges() {
        return charges;
    }

    public void setCharges(List<Charges> charges) {
        this.charges = charges;
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
