package com.softnaptics.training.domain.billing.rendering;

import com.softnaptics.training.domain.billing.Activity;
import com.softnaptics.training.domain.billing.utils.ActivityType;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Audrik !
 */
public class BasicStreamUsingRenderer implements Renderer {
    private InvoiceRenderable invoice;

    private Map<ActivityType, List<Activity>> groupedActivities;

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
        final Set<Activity> activities = invoice.getActivities();
        groupedActivities = activities.stream().collect(Collectors.groupingBy(a -> a.getType()));
    }

    private void print() {
        for (ActivityType activityType : groupedActivities.keySet()) {
            System.out.println(String.format("--------- %s ---------", activityType.name()));
            final double amountHT = groupedActivities.get(activityType).parallelStream().mapToDouble(a -> a.getAmountHT()).sum();
            System.out.println(String.format("Total HT : %.2f €", amountHT));
        }
    }
}
