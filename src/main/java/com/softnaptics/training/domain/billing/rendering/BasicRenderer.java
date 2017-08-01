package com.softnaptics.training.domain.billing.rendering;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.softnaptics.training.domain.billing.Activity;
import com.softnaptics.training.domain.billing.utils.ActivityType;

import java.util.Collection;
import java.util.Set;

/**
 * @author Audrik !
 */
public class BasicRenderer implements Renderer {
    private InvoiceRenderable invoice;

    /** Used for group activities by type */
    private Multimap<ActivityType, Activity> groupedActivities;

    @Override
    public void visit(InvoiceRenderable invoice) {
        this.invoice = invoice;
    }

    @Override
    public void render() {
        groupActivities();
        print();
    }

    private void groupActivities() {
        groupedActivities = ArrayListMultimap.create();

        final Set<Activity> activities = invoice.getActivities();
        if (activities != null) {
            for (final Activity activity : activities) {
                putActivity(activity);
            }
        }
    }

    private void putActivity(final Activity activity) {
        groupedActivities.put(activity.getType(), activity);
    }

    private void print() {
        for (ActivityType activityType : groupedActivities.keySet()) {
            System.out.println(String.format("--------- %s ---------", activityType.name()));

            final double amountHT = getAmountHTOfList(groupedActivities.get(activityType));
            System.out.println(String.format("Total HT : %f €", amountHT));
        }
    }

    private double getAmountHTOfList(final Collection<Activity> activities) {
        double amount = 0;
        for (final Activity activity : activities) {
            amount += activity.getAmountHT();
        }
        return amount;
    }
}
