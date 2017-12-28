package com.softnaptics.impots.utils;

public class Tranche {
    private final double min;
    private final double max;
    private final double rate;

    public Tranche(double min, double max, double rate) {
        this.min = min;
        this.max = max;
        this.rate = rate;
    }

    public boolean shouldCharge(double amount) {
        return amount >= min;
    }

    public double getCharges(double amount) {
        double toCharge;

        if (max - amount <= 0) {
            toCharge = max - min;
        } else {
            toCharge = amount - min;
        }

        return toCharge * rate;
    }
}
