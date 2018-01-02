package com.softnaptics.comptarenderer.invoice;

public class Amounts {

    private double amountHT;
    private double amountTTC;
    private final String RESULT_HT_FORMAT = "Montant total HT : %.2f€".intern();
    private final String RESULT_TTC_FORMAT = "Montant total TTC : %.2f€".intern();

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
        System.out.println(getResults());
    }

    public String getResults() {
        final StringBuilder result = new StringBuilder();
        result.append(String.format(RESULT_HT_FORMAT, amountHT)).append("\n");
        result.append(String.format(RESULT_TTC_FORMAT, amountTTC)).append("\n");

        return result.toString();
    }
}
