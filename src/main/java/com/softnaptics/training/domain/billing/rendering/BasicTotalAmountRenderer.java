package com.softnaptics.training.domain.billing.rendering;

import com.softnaptics.training.domain.billing.Activity;

/**
 * Display only the HT and TTC total amounts
 */
public class BasicTotalAmountRenderer implements Renderer {
    private InvoiceRenderable invoice;

    @Override
    public void visit(InvoiceRenderable invoice) {
        this.invoice = invoice;
    }

    @Override
    public void render() {
        double amountHT = 0;
        double amountTTC = 0;
        for (final Activity activity : invoice.getActivities()) {
            amountHT += activity.getAmountHT();
            amountTTC += activity.getAmountTTC();
        }
        System.out.println("-------------- Basic Recap facture --------------");
        System.out.println(String.format("Total HT : %.2f €", amountHT));
        System.out.println(String.format("Total TTC : %.2f €", amountTTC));
    }
}
