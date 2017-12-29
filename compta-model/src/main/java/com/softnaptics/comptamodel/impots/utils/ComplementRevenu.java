package com.softnaptics.comptamodel.impots.utils;

public class ComplementRevenu {
    private final double amount;
    private final double abattementRate;

    public ComplementRevenu(double amount, double abattementRate) {
        this.amount = amount;
        this.abattementRate = abattementRate;
    }

    public double getOriginalAmount() {
        return amount;
    }

    public double getTaxableAmount() {
        return amount * (1 - abattementRate);
    }
}
