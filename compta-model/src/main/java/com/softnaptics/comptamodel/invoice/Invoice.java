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
    private Long id;

    private String name;
    private Month month;

    //TODO Replace all these collections (List) by Set to improve performances
    @Transient
    private List<AbstractEntry> entries;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
    private List<Activity> activities;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
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

    private void addEntry(AbstractEntry entry) {
        entries.add(entry);
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
        addEntry(activity);
    }

    public void addCharge(Charges charge) {
        charges.add(charge);
        addEntry(charge);
    }

    public List<AbstractEntry> getEntries() {
        return entries;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        addNewEntries(this.activities, activities);
        this.activities = activities;
    }

    public List<Charges> getCharges() {
        return charges;
    }

    public void setCharges(List<Charges> charges) {
        addNewEntries(this.charges, charges);
        this.charges = charges;
    }

    private <E extends AbstractEntry> void addNewEntries(List<E> olds, List<E> news) {
        entries.removeAll(olds);
        entries.addAll(news);
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
