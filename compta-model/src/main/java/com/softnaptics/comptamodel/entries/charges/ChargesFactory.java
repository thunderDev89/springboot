package com.softnaptics.comptamodel.entries.charges;

import com.softnaptics.comptamodel.entries.EntryType;

import java.util.Date;

public class ChargesFactory {

    public static Charges createRemuneration(double amount, Date date) {

        return new Charges(
                "Remuneration",
                0,
                amount,
                date,
                EntryType.REMUNERATION);
    }

    public static Charges createExpenseReport(String name, double tva, double amount, Date date) {

        return new Charges(
                name,
                tva,
                amount,
                date,
                EntryType.EXPENSE_REPORT
        );
    }

    public static Charges createRestaurantCharge(double amount, Date date) {
        return new Charges(
                "Restaurant",
                .1,
                amount,
                date,
                EntryType.EXPENSE_REPORT
        );
    }
}
