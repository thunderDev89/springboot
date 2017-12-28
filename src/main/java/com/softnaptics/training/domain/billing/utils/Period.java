package com.softnaptics.training.domain.billing.utils;

import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Period {

    @Enumerated(EnumType.STRING)
    @Column(name = "period_type")
    private Type type = Type.DAY;

    @Column(name = "period_count")
    private @NonNull
    Double count;

    private Period() {
    }

    private Period(Type type, double count) {
        this.type = type;
        this.count = count;
    }

    public static Period forDays(double count) {
        return new Period(Type.DAY, count);
    }

    public static Period forHours(double count) {
        return new Period(Type.HOURS, count);
    }

    public Double getCountToDayType() {
        if (Type.DAY == type) {
            return count;
        }
        return count / 8;
    }

    public Double getCountToHoursType() {
        if (Type.HOURS == type) {
            return count;
        }
        return count * 8;
    }

    public enum Type {
        DAY, HOURS
    }
}
