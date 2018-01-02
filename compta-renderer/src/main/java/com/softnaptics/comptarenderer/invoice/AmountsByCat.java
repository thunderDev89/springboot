package com.softnaptics.comptarenderer.invoice;

public class AmountsByCat extends Amounts {
    private double nbPeriod; // By DAY
    private double amountHT;
    private double amountTTC;
    private final String type;

    public AmountsByCat(String type) {
        amountHT = 0;
        amountTTC = 0;
        nbPeriod = 0;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public double getNbPeriod() {
        return nbPeriod;
    }

    public double getAmountHT() {
        return amountHT;
    }

    public double getAmountTTC() {
        return amountTTC;
    }

    public void addPeriodByDay(double nbPeriodByDay) {
        nbPeriod += nbPeriodByDay;
    }
    public void addAmountHT(double amounHTToAdd) {
        amountHT += amounHTToAdd;
    }
    public void addAmountTTC(double amounTTCToAdd) {
        amountTTC += amounTTCToAdd;
    }
}
