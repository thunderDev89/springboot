package com.softnaptics.comptarenderer.invoice;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.softnaptics.comptamodel.entries.Entry;
import com.softnaptics.comptamodel.entries.EntryType;
import com.softnaptics.comptamodel.entries.activity.Activity;
import com.softnaptics.comptamodel.entries.activity.property.ActivityType;
import com.softnaptics.comptarenderer.Renderer;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class InvoiceActivitiesByTypeRenderer implements Renderer {

    private ListMultimap<ActivityType, Activity> activitiesByType;
    private Map<ActivityType, Amounts> mapAmounts;

    public InvoiceActivitiesByTypeRenderer() {
        activitiesByType = ArrayListMultimap.create();
        mapAmounts = Maps.newHashMap();
    }

    public InvoiceActivitiesByTypeRenderer(List<Entry> entries) {
        this();

        for (final Entry entry : entries) {
            // Add only entries of type Activity
            if (EntryType.ACTIVITY.equals(entry.getEntryType())) {
                /*TODO Create special activity interface to be handle by this renderer,
                    Instead of make accessible all the fields of the Activity's class
                */
                final Activity activity = (Activity) entry;
                activitiesByType.put(activity.getType(), activity);
            }
        }

        for (final Map.Entry<ActivityType, Collection<Activity>> entry :
                activitiesByType.asMap().entrySet()) {

            final ActivityType type = entry.getKey();
            final Collection<Activity> invoiceActivities = entry.getValue();

            final Amounts amounts = new Amounts();
            mapAmounts.put(type, amounts);

            for (final Activity activity : invoiceActivities) {
                amounts.addAmountHT(activity.getAmountHT() * activity.getQty());
                amounts.addAmountTTC(activity.getAmountHT() * activity.getQty() * (1 + activity.getTVA()));
            }
        }
    }

    @Override
    public void render() {
        for (Map.Entry<ActivityType, Amounts> amountsEntry : mapAmounts.entrySet()) {
            final Amounts amounts = amountsEntry.getValue();
            System.out.println(String.format("\n------- %s --------", amountsEntry.getKey()));
            amounts.print();
        }
    }

    public void renderTotal() {
        final Amounts amounts = new Amounts();

        for (Amounts entryAmounts :
                mapAmounts.values()) {
            amounts.addAmountHT(entryAmounts.getAmountHT());
            amounts.addAmountTTC(entryAmounts.getAmountTTC());
        }
        System.out.println(String.format("\n------- %s --------", "TOTAUX"));
        amounts.print();


    }
}
