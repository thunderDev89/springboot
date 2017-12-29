package com.softnaptics.comptamodel.impots.utils;

public class ComplementCharge {
    private final String name;
    private final double rate;

    public ComplementCharge(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }
}
