package com.softnaptics.comptaservice;

import com.softnaptics.comptamodel.impots.builder.IR;
import org.springframework.stereotype.Service;

@Service
public class ImpotService {

    public double computeIR(double revenus) {

        IR ir = new IR.Builder()
                .baseRevenu(revenus)
                .includeCsgCrds()
                .build();

        return ir.calculateTax();
    }
}
