package com.softnaptics.comptarenderer.invoice.tostring;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.softnaptics.comptamodel.entries.AbstractEntry;
import com.softnaptics.comptamodel.entries.EntryType;
import com.softnaptics.comptarenderer.Renderer;
import com.softnaptics.comptarenderer.invoice.Amounts;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class InvoiceEntryByTypeRenderer implements Renderer {

    private ListMultimap<EntryType, AbstractEntry> mapEntriesByType;
    private Map<EntryType, Amounts> mapAmounts;

    public InvoiceEntryByTypeRenderer() {
        mapEntriesByType = ArrayListMultimap.create();
        mapAmounts = Maps.newHashMap();
    }

    public InvoiceEntryByTypeRenderer(List<AbstractEntry> entriesOfInvoice) {
        this();
        for (AbstractEntry entry : entriesOfInvoice) {
            mapEntriesByType.put(entry.getEntryType(), entry);
        }
        for (final Map.Entry<EntryType, Collection<AbstractEntry>> mapEntry :
                mapEntriesByType.asMap().entrySet()) {

            final EntryType type = mapEntry.getKey();
            final Collection<AbstractEntry> invoiceEntries = mapEntry.getValue();

            final Amounts amounts = new Amounts();
            mapAmounts.put(type, amounts);

            for (final AbstractEntry entry : invoiceEntries) {
                amounts.addAmountHT(entry.getUnitPriceHT() * entry.getQty());
                amounts.addAmountTTC(entry.getUnitPriceHT() * entry.getQty() * (1 + entry.getTVA()));
            }
        }
    }

    @Override
    public void render() {
        System.out.println(getResults());
    }

    public String getResults() {
        final StringBuilder results = new StringBuilder();

        for (Map.Entry<EntryType, Amounts> amountsEntry : mapAmounts.entrySet()) {
            final Amounts amounts = amountsEntry.getValue();
            results.append("\n").append("------- ").append(amountsEntry.getKey()).append(" -------");
            //System.out.println(String.format("\n------- %s --------", amountsEntry.getKey()));
            results.append("\n").append(amounts.getResults());
            //amounts.print();
        }

        return results.toString();
    }
}
