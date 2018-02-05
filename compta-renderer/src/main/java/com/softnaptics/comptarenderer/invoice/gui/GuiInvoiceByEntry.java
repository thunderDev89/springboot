package com.softnaptics.comptarenderer.invoice.gui;

import com.softnaptics.comptamodel.entries.AbstractEntry;
import com.softnaptics.comptamodel.entries.EntryType;

import java.util.List;

public class GuiInvoiceByEntry extends AmountByTypeRenderer<EntryType, AbstractEntry> {

    public GuiInvoiceByEntry(List<AbstractEntry> invoiceEntries) {
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
