package com.softnaptics.comptamodel.entries.activity.property;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class ActivityPeriod {

    @Column(name = "period_count")
    private final double count;

    @Enumerated(EnumType.STRING)
    @Column(name = "period_type")
    private final PeriodType type;

    private ActivityPeriod(double count, PeriodType type) {
        this.count = count;
        this.type = type;
    }

    public static ActivityPeriod forDays(double count) {
        return new ActivityPeriod(count, PeriodType.DAY);
    }
    public static ActivityPeriod forHours(double count) {
        return new ActivityPeriod(count, PeriodType.HOUR);
    }

    public double getCount() {
        return count;
    }

    public double getCountInDayType() {
        if (PeriodType.HOUR == type) {
            return count / 8;
        }
        return count;
    }

    public PeriodType getType() {
        return type;
    }

    @Embeddable
    public enum PeriodType {
        DAY, HOUR
    }
}
