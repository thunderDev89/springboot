package com.softnaptics.comptarenderer.invoice.tostring;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.softnaptics.comptamodel.entries.AbstractEntry;
import com.softnaptics.comptarenderer.Renderer;
import com.softnaptics.comptarenderer.invoice.Amounts;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class ByTypeRenderer<T, E extends AbstractEntry> implements Renderer {

    private ListMultimap<T, E> byTypeMap;
    private Map<T, Amounts> mapAmounts;

    private ByTypeRenderer() {
        byTypeMap = ArrayListMultimap.create();
        mapAmounts = Maps.newHashMap();
    }

    public ByTypeRenderer(List<AbstractEntry> invoiceEntries) {
        this();

        for (final AbstractEntry invoiceEntry : invoiceEntries) {
            if (shouldAddEntry(invoiceEntry)) {
                addEntry(invoiceEntry);
            }
        }

        for (final Map.Entry<T, Collection<E>> entry :
                byTypeMap.asMap().entrySet()) {

            final T type = entry.getKey();
            final Collection<E> values = entry.getValue();

            final Amounts amounts = new Amounts();
            mapAmounts.put(type, amounts);

            for (final E value : values) {
                amounts.addAmountHT(value.getUnitPriceHT() * value.getQty());
                amounts.addAmountTTC(value.getUnitPriceHT() * value.getQty() * (1 + value.getTVA()));
            }
        }
    }

    @Override
    public void render() {
        for (Map.Entry<T, Amounts> amountsEntry : mapAmounts.entrySet()) {
            final Amounts amounts = amountsEntry.getValue();
            System.out.println(String.format("\n------- %s --------", amountsEntry.getKey()));
            amounts.print();
        }
    }

    public void renderTotalAmounts() {
        final Amounts amounts = new Amounts();

        for (Amounts entryAmounts :
                mapAmounts.values()) {
            amounts.addAmountHT(entryAmounts.getAmountHT());
            amounts.addAmountTTC(entryAmounts.getAmountTTC());
        }
        System.out.println(String.format("\n================= %s =================", "TOTAUX"));
        amounts.print();
    }

    protected abstract boolean shouldAddEntry(AbstractEntry invoiceEntry);

    protected void addEntry(AbstractEntry invoiceEntry) {
        byTypeMap.put(getEntryType(invoiceEntry), (E) invoiceEntry);
    }

    abstract protected T getEntryType(AbstractEntry entry);
}
