package com.softnaptics.training.domain.billing;

import com.softnaptics.training.domain.billing.rendering.InvoiceRenderable;
import com.softnaptics.training.domain.billing.rendering.Renderer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Audrik !
 */
public class Invoice implements InvoiceRenderable {
    private Long id;

    private String name;

    private int monthNumber;

    private Set<Activity> activities;

    public Invoice(String name, int monthNumber) {
        this.name = name;
        this.monthNumber = monthNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Activity> getActivities() {
        return new HashSet<>(activities);
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public void addActivity(Activity activity) {
        if (activities == null) {
            activities = new HashSet<>();
        }
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activity != null) {
            activities.remove(activity);
        }
    }

    @Override
    public void accept(Renderer renderer) {
        renderer.visit(this);
    }

    public void print() {
        double amntHT = 0;
        double amntTTC = 0;
        for (Activity activity : activities) {
            amntHT += activity.getAmountHT();
            amntTTC += activity.getAmountTTC();
        }
        System.out.println(String.format("Montant total HT : %.2f €", amntHT));
        System.out.println(String.format("Montant total TTC : %.2f €", amntTTC));
    }
}