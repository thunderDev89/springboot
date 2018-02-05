package com.softnaptics.comptarenderer.invoice.gui;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.softnaptics.comptamodel.entries.AbstractEntry;
import com.softnaptics.comptarenderer.GuiRenderer;
import com.softnaptics.comptarenderer.invoice.AmountsByCat;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class AmountByTypeRenderer<T, E extends AbstractEntry> implements GuiRenderer {
    private ListMultimap<T, E> byTypeMap;
    private List<AmountsByCat> amountsList;

    private AmountByTypeRenderer() {
        byTypeMap = ArrayListMultimap.create();
        amountsList = Lists.newArrayList();
    }

    public AmountByTypeRenderer(List<E> invoiceEntries) {
        this();

        for (final AbstractEntry invoiceEntry : invoiceEntries) {
            if (shouldAddEntry(invoiceEntry)) {
                addEntry(invoiceEntry);
            }
        }

        calculateAmounts();
    }

    private void calculateAmounts() {
        for (final Map.Entry<T, Collection<E>> entry :
                byTypeMap.asMap().entrySet()) {

            final T type = entry.getKey();
            final Collection<E> values = entry.getValue();

            final AmountsByCat amounts = new AmountsByCat(type.toString());
            amountsList.add(amounts);

            for (final E value : values) {
                amounts.addPeriodByDay(1);
                amounts.addAmountHT(value.getUnitPriceHT() * value.getQty());
                amounts.addAmountTTC(value.getUnitPriceHT() * value.getQty() * (1 + value.getTVA()));
            }
        }
    }

    protected abstract boolean shouldAddEntry(AbstractEntry invoiceEntry);

    protected void addEntry(AbstractEntry invoiceEntry) {
        byTypeMap.put(getEntryType(invoiceEntry), (E) invoiceEntry);
    }

    abstract protected T getEntryType(AbstractEntry entry);

    @Override
    public List<AmountsByCat> getAmountsList() {
        return amountsList;
    }
}
