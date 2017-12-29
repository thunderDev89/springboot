package com.softnaptics.comptamodel.impots;

import com.softnaptics.comptamodel.impots.utils.Tranche;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TaxEvaluator {

    private final List<Tranche> tranches;

    public TaxEvaluator() {
        this.tranches = new ArrayList<>(4);
    }

    public void addTranche(final Tranche tranche) {
        tranches.add(tranche);
    }

    public void addTranches(final List<Tranche> tranches) {
        this.tranches.addAll(tranches);
    }

    public double evaluateTax(double amount) {
        double charges = 0;
        for (Tranche tranche :
                tranches) {
            if (tranche.shouldCharge(amount)) {
                charges += tranche.getCharges(amount);
            } else {
                break;
            }
        }

        return charges;
    }
}
