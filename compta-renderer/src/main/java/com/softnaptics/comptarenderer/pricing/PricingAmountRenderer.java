package com.softnaptics.comptarenderer.pricing;

import com.softnaptics.comptamodel.entries.Entry;
import com.softnaptics.comptarenderer.Renderer;

import java.util.List;

public class PricingAmountRenderer implements Renderer {

    private final List<Entry> entries;
    private double amountHT;
    private double amountTTC;

    public PricingAmountRenderer(List<Entry> entries) {
        this.entries = entries;
        amountHT = 0;
        amountTTC = 0;
        computeAmountsToRender();
    }

    @Override
    public void render() {
        System.out.println(String.format("Montant total HT : %.2f€", amountHT));
        System.out.println(String.format("Montant total TTC : %.2f€", amountTTC));
    }

    private void computeAmountsToRender() {
        for (Entry entry :
                entries) {
            amountHT += entry.getUnitPriceHT() * entry.getQty();
            amountTTC += entry.getUnitPriceHT() * entry.getQty() * (1 + entry.getTVA());
        }
    }

}
