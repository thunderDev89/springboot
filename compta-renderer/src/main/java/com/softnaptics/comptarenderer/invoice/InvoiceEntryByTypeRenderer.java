package com.softnaptics.comptarenderer.invoice;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.softnaptics.comptamodel.entries.Entry;
import com.softnaptics.comptamodel.entries.EntryType;
import com.softnaptics.comptarenderer.Renderer;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class InvoiceEntryByTypeRenderer implements Renderer {

    private ListMultimap<EntryType, Entry> mapEntriesByType;
    private Map<EntryType, Amounts> mapAmounts;

    public InvoiceEntryByTypeRenderer() {
        mapEntriesByType = ArrayListMultimap.create();
        mapAmounts = Maps.newHashMap();
    }

    public InvoiceEntryByTypeRenderer(List<Entry> entriesOfInvoice) {
        this();
        for (Entry entry : entriesOfInvoice) {
            mapEntriesByType.put(entry.getEntryType(), entry);
        }
        for (final Map.Entry<EntryType, Collection<Entry>> mapEntry :
                mapEntriesByType.asMap().entrySet()) {

            final EntryType type = mapEntry.getKey();
            final Collection<Entry> invoiceEntries = mapEntry.getValue();

            final Amounts amounts = new Amounts();
            mapAmounts.put(type, amounts);

            for (final Entry entry : invoiceEntries) {
                amounts.addAmountHT(entry.getUnitPriceHT() * entry.getQty());
                amounts.addAmountTTC(entry.getUnitPriceHT() * entry.getQty() * (1 + entry.getTVA()));
            }
        }
    }

    @Override
    public void render() {
        for (Map.Entry<EntryType, Amounts> amountsEntry : mapAmounts.entrySet()) {
            final Amounts amounts = amountsEntry.getValue();
            System.out.println(String.format("\n------- %s --------", amountsEntry.getKey()));
            amounts.print();
        }
    }
}
