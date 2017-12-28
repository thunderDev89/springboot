package com.softnaptics.impots;

import com.google.common.collect.Sets;
import com.softnaptics.impots.utils.ComplementCharge;
import com.softnaptics.impots.utils.ComplementRevenu;
import com.softnaptics.impots.utils.Tranche;

import java.util.Set;

public class IR {
    private final TaxEvaluator calculator = new TaxEvaluator();

    private final Tranche tranche1;
    private final Tranche tranche2;
    private final Tranche tranche3;
    private final Tranche tranche4;

    private final Set<ComplementRevenu> complementsRevenus;
    private final Set<ComplementCharge> complementsCharges;

    public IR() {
        tranche1 = new Tranche(9700, 26791, .15);
        tranche2 = new Tranche(26791, 71826, .3);
        tranche3 = new Tranche(71826, 152108, .41);
        tranche4 = new Tranche(152108, 1000000000, .45);

        calculator.addTranche(tranche1);
        calculator.addTranche(tranche2);
        calculator.addTranche(tranche3);
        calculator.addTranche(tranche4);

        complementsCharges = Sets.newHashSet();
        complementsRevenus = Sets.newHashSet();
    }

    public void addComplementRevenu(double amount, double abattement) {
        complementsRevenus.add(new ComplementRevenu(amount, abattement));
    }

    public void addComplementCharges(String name, double rate) {
        complementsCharges.add(new ComplementCharge(name, rate));
    }

    public double calculateTax() {
        double totalAmount = 0;
        double taxableAmount = 0;

        for (ComplementRevenu complementRevenu :
                complementsRevenus) {
            totalAmount += complementRevenu.getOriginalAmount();
            taxableAmount += complementRevenu.getTaxableAmount();
        }

        double charges = 0;
        for (ComplementCharge complementCharge :
                complementsCharges) {
            charges += totalAmount * complementCharge.getRate();
        }

        return calculator.evaluateTax(taxableAmount+charges);
    }



}
