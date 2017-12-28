package com.softnaptics.comptarenderer.invoice;

public class Amounts {

    private double amountHT;
    private double amountTTC;

    public Amounts() {
        amountHT = 0;
        amountTTC = 0;
    }

    public double getAmountHT() {
        return amountHT;
    }

    public double getAmountTTC() {
        return amountTTC;
    }

    public void addAmountHT(double amounHTToAdd) {
        amountHT += amounHTToAdd;
    }
    public void addAmountTTC(double amounTTCToAdd) {
        amountTTC += amounTTCToAdd;
    }

    public void print() {
        System.out.println(String.format("Montant total HT : %.2f€", amountHT));
        System.out.println(String.format("Montant total TTC : %.2f€", amountTTC));
    }
}
