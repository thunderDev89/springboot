package com.softnaptics.comptarenderer.invoice;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.softnaptics.comptamodel.entries.activity.Activity;
import com.softnaptics.comptamodel.entries.activity.property.ActivityType;
import com.softnaptics.comptarenderer.Renderer;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class InvoiceActivityAmountsByType implements Renderer {
    private ListMultimap<ActivityType, Activity> activitiesByType;
    private List<AmountsByCat> amountsList;

    private InvoiceActivityAmountsByType() {
        activitiesByType = ArrayListMultimap.create();
        amountsList = Lists.newArrayList();
    }

    public InvoiceActivityAmountsByType(List<Activity> activities) {
        this();

        for (final Activity activity : activities) {
            // Add only entries of type Activity
            activitiesByType.put(activity.getType(), activity);
        }

        //TODO calculate amounts
        calculateAmounts();
    }

    private void calculateAmounts() {
        for (final Map.Entry<ActivityType, Collection<Activity>> entry :
                activitiesByType.asMap().entrySet()) {

            final ActivityType type = entry.getKey();
            final Collection<Activity> invoiceActivities = entry.getValue();

            final AmountsByCat amounts = new AmountsByCat(type.name());
            amountsList.add(amounts);

            for (final Activity activity : invoiceActivities) {
                amounts.addPeriodByDay(activity.getPeriod().getCountInDayType());
                amounts.addAmountHT(activity.getUnitPriceHT() * activity.getQty());
                amounts.addAmountTTC(activity.getUnitPriceHT() * activity.getQty() * (1 + activity.getTVA()));
            }
        }
    }

    @Override
    public void render() {

    }

    public List<AmountsByCat> getAmountsList() {
        return amountsList;
    }
}
