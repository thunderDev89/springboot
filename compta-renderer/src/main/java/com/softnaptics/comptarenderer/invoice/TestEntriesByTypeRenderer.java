package com.softnaptics.comptarenderer.invoice;

import com.softnaptics.comptamodel.entries.Entry;
import com.softnaptics.comptamodel.entries.EntryType;

import java.util.List;

public class TestEntriesByTypeRenderer extends ByTypeRenderer<EntryType, Entry> {

    public TestEntriesByTypeRenderer(List<Entry> invoiceEntries) {
        super(invoiceEntries);
    }

    @Override
    protected boolean shouldAddEntry(Entry invoiceEntry) {
        return true;
    }

    @Override
    protected EntryType getEntryType(Entry entry) {
        return entry.getEntryType();
    }
}
