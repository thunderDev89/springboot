package com.softnaptics.training.domain.billing.rendering;

import com.softnaptics.training.domain.billing.Activity;
import com.softnaptics.training.domain.billing.utils.ActivityType;

/**
 * Display amounts by normal or duty activities
 */
public class BasicTotalAmount2Renderer implements Renderer {
    private InvoiceRenderable invoice;

    @Override
    public void visit(InvoiceRenderable invoice) {
        this.invoice = invoice;
    }

    @Override
    public void render() {
        final double amountHT = invoice.getActivities().stream()
                .filter(a -> a.getType() == ActivityType.SEMAINE_ACTIF)
                .findFirst()
                .map(Activity::getAmountHT)
                .orElse(0.0);

        final double amountDutyHT = invoice.getActivities().stream()
                .filter(a -> a.getType() != ActivityType.SEMAINE_ACTIF)
                .mapToDouble(a -> a.getAmountHT())
                .sum();
//                .map(Activity::getAmountHT)
//                .reduce((amnt1, amnt2) -> amnt1 + amnt2)
//                .ifPresent(Double::valueOf);


        System.out.println(String.format("HEURES NORMALES :: Total HT : %.2f €", amountHT));
        System.out.println(String.format("ASTREINTES :: Total TTC : %.2f €", amountDutyHT));
    }
}
