package com.softnaptics.comptarenderer.invoice.tostring;

import com.softnaptics.comptamodel.entries.AbstractEntry;
import com.softnaptics.comptamodel.entries.EntryType;

import java.util.List;

public class TestEntriesByTypeRenderer extends ByTypeRenderer<EntryType, AbstractEntry> {

    public TestEntriesByTypeRenderer(List<AbstractEntry> invoiceEntries) {
        super(invoiceEntries);
    }

    @Override
    protected boolean shouldAddEntry(AbstractEntry invoiceEntry) {
        return true;
    }

    @Override
    protected EntryType getEntryType(AbstractEntry entry) {
        return entry.getEntryType();
    }
}
