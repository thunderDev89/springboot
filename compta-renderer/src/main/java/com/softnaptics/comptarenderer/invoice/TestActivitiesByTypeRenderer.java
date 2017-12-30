package com.softnaptics.comptarenderer.invoice;

import com.softnaptics.comptamodel.entries.AbstractEntry;
import com.softnaptics.comptamodel.entries.EntryType;
import com.softnaptics.comptamodel.entries.activity.Activity;
import com.softnaptics.comptamodel.entries.activity.property.ActivityType;

import java.util.List;

public class TestActivitiesByTypeRenderer extends ByTypeRenderer<ActivityType, Activity> {

    public TestActivitiesByTypeRenderer(List<AbstractEntry> invoiceEntries) {
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
