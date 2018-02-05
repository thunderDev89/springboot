package com.softnaptics.comptarenderer.invoice.gui;

import com.softnaptics.comptamodel.entries.AbstractEntry;
import com.softnaptics.comptamodel.entries.EntryType;
import com.softnaptics.comptamodel.entries.activity.Activity;
import com.softnaptics.comptamodel.entries.activity.property.ActivityType;

import java.util.List;

public class GuiInvoiceByActivity extends AmountByTypeRenderer<ActivityType, Activity> {

    public GuiInvoiceByActivity(List<Activity> invoiceEntries) {
        super(invoiceEntries);
    }

    @Override
    protected boolean shouldAddEntry(AbstractEntry invoiceEntry) {
        return EntryType.ACTIVITY.equals(invoiceEntry.getEntryType());
    }

    @Override
    protected ActivityType getEntryType(AbstractEntry entry) {
        return ((Activity) entry).getType();
    }
}
