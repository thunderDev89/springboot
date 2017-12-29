package com.softnaptics.comptamodel.impots;

import com.google.common.collect.Sets;
import com.softnaptics.comptamodel.impots.utils.ComplementCharge;
import com.softnaptics.comptamodel.impots.utils.ComplementRevenu;

import java.util.Set;

public class ChargesCalculator {

    private final Set<ComplementRevenu> complementsRevenus;
    private final Set<ComplementCharge> complementsCharges;

    public ChargesCalculator() {
        complementsRevenus = Sets.newHashSet();
        complementsCharges = Sets.newHashSet();
    }

    public void addComplementRevenus(Set<ComplementRevenu> complementRevenus) {
        this.complementsRevenus.addAll(complementRevenus);
    }

    public void addComplementCharges(Set<ComplementCharge> complementCharges) {
        this.complementsCharges.addAll(complementCharges);
    }

    public double calculateTaxableAmount() {
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

        return taxableAmount + charges;
    }
}
