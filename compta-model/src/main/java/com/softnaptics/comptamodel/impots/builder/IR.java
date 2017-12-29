package com.softnaptics.comptamodel.impots.builder;

import com.google.common.collect.Sets;
import com.softnaptics.comptamodel.impots.ChargesCalculator;
import com.softnaptics.comptamodel.impots.TaxEvaluator;
import com.softnaptics.comptamodel.impots.utils.ComplementCharge;
import com.softnaptics.comptamodel.impots.utils.ComplementRevenu;
import com.softnaptics.comptamodel.impots.utils.Tranche;

import java.util.Arrays;
import java.util.Set;

public class IR {
    private final TaxEvaluator taxEvaluator = new TaxEvaluator();
    private final ChargesCalculator calculator = new ChargesCalculator();


    private IR(Builder builder) {
        final Tranche tranche1 = new Tranche(9700, 26791, .15);
        final Tranche tranche2 = new Tranche(26791, 71826, .3);
        final Tranche tranche3 = new Tranche(71826, 152108, .41);
        final Tranche tranche4 = new Tranche(152108, 1000000000, .45);

        taxEvaluator.addTranches(Arrays.asList(tranche1, tranche2, tranche3, tranche4));

        calculator.addComplementRevenus(builder.complementsRevenus);
        calculator.addComplementCharges(builder.complementsCharges);
    }

    public double calculateTax() {
        return taxEvaluator.evaluateTax(calculator.calculateTaxableAmount());
    }

    public static class Builder {
        private double baseRevenu = -1;
        private final Set<ComplementRevenu> complementsRevenus = Sets.newHashSet();
        private final Set<ComplementCharge> complementsCharges = Sets.newHashSet();

        public Builder baseRevenu(final double amount) {
            baseRevenu = amount;
            complementsRevenus.add(new ComplementRevenu(amount, .1));
            return this;
        }

        public Builder microFoncier(final double amount) {
            complementsRevenus.add(new ComplementRevenu(amount, .3));
            return this;
        }

        public Builder includeCsgCrds() {
            complementsCharges.add(new ComplementCharge("CSG_CRDS", .029 * 1.37));
            return this;
        }

        public IR build() {
            if (baseRevenu == -1) {
                throw new IllegalStateException("You should give at least the base revenu to calculate your taxes");
            }
            return new IR(this);
        }
    }
}
