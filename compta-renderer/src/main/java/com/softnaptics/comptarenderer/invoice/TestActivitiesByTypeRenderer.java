package com.softnaptics.comptarenderer.invoice;

import com.softnaptics.comptamodel.entries.Entry;
import com.softnaptics.comptamodel.entries.EntryType;
import com.softnaptics.comptamodel.entries.activity.Activity;
import com.softnaptics.comptamodel.entries.activity.property.ActivityType;

import java.util.List;

public class TestActivitiesByTypeRenderer extends ByTypeRenderer<ActivityType, Activity> {

    public TestActivitiesByTypeRenderer(List<Entry> invoiceEntries) {
        super(invoiceEntries);
    }

    @Override
    protected boolean shouldAddEntry(Entry invoiceEntry) {
        return EntryType.ACTIVITY.equals(invoiceEntry.getEntryType());
    }

    @Override
    protected ActivityType getEntryType(Entry entry) {
        return ((Activity) entry).getType();
    }
}
