package com.softnaptics.comptarenderer.launcher;

import com.softnaptics.comptamodel.entries.activity.Activity;
import com.softnaptics.comptamodel.entries.activity.property.ActivityPeriod;
import com.softnaptics.comptamodel.entries.activity.property.ActivityType;
import com.softnaptics.comptamodel.entries.expensereport.ExpenseReport;
import com.softnaptics.comptamodel.entries.remuneration.Remuneration;
import com.softnaptics.comptamodel.entries.utils.DateUtils;
import com.softnaptics.comptamodel.impots.IR;
import com.softnaptics.comptamodel.invoice.Invoice;
import com.softnaptics.comptarenderer.invoice.TestActivitiesByTypeRenderer;
import com.softnaptics.comptarenderer.invoice.TestEntriesByTypeRenderer;
import com.softnaptics.comptarenderer.pricing.PricingAmountRenderer;

import java.time.LocalDate;
import java.time.Month;

public class Launcher {

    public static void main(String[] args) {
        julyInvoice();
        //impotRevenus(34000);
    }

    public static void impotRevenus(double amount) {
        //System.out.println("Montant impôts " + IR.getInstance().getCharges(amount));

        IR impotsRevenus = new IR();

        impotsRevenus.addComplementRevenu(amount, .1); // Revenus de base
        impotsRevenus.addComplementRevenu(2500, .3); //Revenus foncier
        impotsRevenus.addComplementCharges("CSG_CRDS", .029 * 1.37);

        System.out.println("Montant impôts : " + impotsRevenus.calculateTax());

        com.softnaptics.comptamodel.impots.builder.IR newIR =
                new com.softnaptics.comptamodel.impots.builder.IR.Builder()
                        .baseRevenu(amount)
                        .microFoncier(2500)
                        .includeCsgCrds()
                        .build();
        System.out.println("Montant impôts - new : " + newIR.calculateTax());

    }

    public static void julyInvoice() {
        final Invoice julyInvoice = new Invoice(
                "Facture Juillet",
                Month.AUGUST,
                DateUtils.asDate(LocalDate.of(2017, 7, 31))
        );

        final double TVA = .2;
        final double TJM = 330;

        final Activity normalDays = new Activity(
                "Journées normales",
                TVA,
                TJM,
                ActivityType.SEMAINE_ACTIF,
                ActivityPeriod.forDays(20),
                DateUtils.asDate(LocalDate.of(2017, 7, 31))
        );
        final Activity astreinteWeeknd = new Activity(
                "Astreintes Weekend",
                TVA,
                TJM,
                ActivityType.SAMEDI_ACTIF,
                ActivityPeriod.forHours(9),
                DateUtils.asDate(LocalDate.of(2017, 7, 8))
        );
        final Activity astreinteFerie = new Activity(
                "Astreinte 14 juillet",
                TVA,
                TJM,
                ActivityType.FERIE_ACTIF,
                ActivityPeriod.forHours(11),
                DateUtils.asDate(LocalDate.of(2017, 7, 14))
        );
        final Activity nightShifts = new Activity(
                "Astreintes passives",
                TVA,
                TJM,
                ActivityType.SEMAINE_NUIT_PASSIF,
                ActivityPeriod.forDays(3),
                DateUtils.asDate(LocalDate.of(2017, 7, 31))
        );
        final Activity weekndNightShift = new Activity(
                "Shift de nuit weeknd",
                TVA,
                TJM,
                ActivityType.FERIE_NUIT_PASSIF,
                ActivityPeriod.forDays(4),
                DateUtils.asDate(LocalDate.of(2017, 7, 29))
        );
        final Activity nightCallingShifts = new Activity(
                "Nuit Appel semaine",
                TVA,
                TJM,
                ActivityType.SEMAINE_NUIT_ACTIF,
                ActivityPeriod.forHours(1),
                DateUtils.asDate(LocalDate.of(2017, 7, 29))
        );
        final Activity suppHours = new Activity(
                "Heures supps",
                TVA,
                TJM,
                ActivityType.HEURES_SUPPS,
                ActivityPeriod.forHours(1),
                DateUtils.asDate(LocalDate.of(2017, 7, 28))
        );

        julyInvoice.addEntry(normalDays);
        julyInvoice.addEntry(astreinteWeeknd);
        julyInvoice.addEntry(astreinteFerie);
        julyInvoice.addEntry(nightShifts);
        julyInvoice.addEntry(weekndNightShift);
        julyInvoice.addEntry(nightCallingShifts);
        julyInvoice.addEntry(suppHours);

        final Remuneration remuneration = new Remuneration(4500, DateUtils.asDate(LocalDate.of(2017, 8, 5)));

        final ExpenseReport ndfTelPro = new ExpenseReport(
                "Achat téléphone",
                540.41,
                .2,
                DateUtils.asDate(LocalDate.of(2017, 8, 15))
        );

        julyInvoice.addEntry(remuneration);
        julyInvoice.addEntry(ndfTelPro);

        final TestActivitiesByTypeRenderer invoiceActivitiesByTypeRenderer = new TestActivitiesByTypeRenderer(julyInvoice.getEntries());
        invoiceActivitiesByTypeRenderer.render();
        invoiceActivitiesByTypeRenderer.renderTotalAmounts();

        System.out.println("\n\n+++++++++++++++++++++++++++++++++++++++++++++++\n\n");

        final TestEntriesByTypeRenderer entriesRenderer = new TestEntriesByTypeRenderer(julyInvoice.getEntries());
        entriesRenderer.render();
        entriesRenderer.renderTotalAmounts();

    }

    public static void octoberInvoice() {
        final Invoice octoberInvoice = new Invoice(
                "Facture octobre",
                Month.OCTOBER,
                DateUtils.asDate(LocalDate.of(2017, 10, 31))
        );

        final double TVA = .2;
        final double TJM = 330;

        final Activity normalDays = new Activity(
                "Journées normales",
                TVA,
                TJM,
                ActivityType.SEMAINE_ACTIF,
                ActivityPeriod.forDays(21.5),
                DateUtils.asDate(LocalDate.of(2017, 10, 31))
        );
        final Activity nightShifts = new Activity(
                "Astreintes passives",
                TVA,
                TJM,
                ActivityType.SEMAINE_NUIT_PASSIF,
                ActivityPeriod.forDays(5),
                DateUtils.asDate(LocalDate.of(2017, 10, 31))
        );
        final Activity nightCallingShifts = new Activity(
                "Nuit Appel semaine",
                TVA,
                TJM,
                ActivityType.SEMAINE_NUIT_ACTIF,
                ActivityPeriod.forHours(2),
                DateUtils.asDate(LocalDate.of(2017, 10, 14))
        );
        final Activity weekndNightShift = new Activity(
                "Shift de nuit weeknd",
                TVA,
                TJM,
                ActivityType.FERIE_NUIT_PASSIF,
                ActivityPeriod.forDays(1),
                DateUtils.asDate(LocalDate.of(2017, 10, 15))
        );
        final Activity suppHours = new Activity(
                "Heures supps",
                TVA,
                TJM,
                ActivityType.HEURES_SUPPS,
                ActivityPeriod.forHours(2),
                DateUtils.asDate(LocalDate.of(2017, 10, 17))
        );

        octoberInvoice.addEntry(normalDays);
        octoberInvoice.addEntry(nightShifts);
        octoberInvoice.addEntry(nightCallingShifts);
        octoberInvoice.addEntry(weekndNightShift);
        octoberInvoice.addEntry(suppHours);

        final PricingAmountRenderer amountRenderer = new PricingAmountRenderer(octoberInvoice.getEntries());
        amountRenderer.render();
    }

    public static void novemberInvoice() {
        final Invoice invoice = new Invoice(
                "Facture de Novembre",
                Month.NOVEMBER,
                DateUtils.asDate(LocalDate.of(2017, 11, 30))
        );

        final double TVA = .2;
        final double TJM = 330;

        final Activity normalDays = new Activity(
                "Journées normales",
                TVA,
                TJM,
                ActivityType.SEMAINE_ACTIF,
                ActivityPeriod.forDays(21),
                DateUtils.asDate(LocalDate.of(2017, 11, 30))
        );
        final Activity firstNovember = new Activity(
                "Ferie 1er Novembre",
                TVA,
                TJM,
                ActivityType.FERIE_ACTIF,
                ActivityPeriod.forHours(10),
                DateUtils.asDate(LocalDate.of(2017, 11, 1))
        );
        final Activity nightShiftFerie = new Activity(
                "Shift de nuit Ferie",
                TVA,
                TJM,
                ActivityType.FERIE_NUIT_PASSIF,
                ActivityPeriod.forDays(1),
                DateUtils.asDate(LocalDate.of(2017, 11, 1))
        );
        final Activity nightCallingShiftFerie = new Activity(
                "Appel shift de nuit Ferie",
                TVA,
                TJM,
                ActivityType.FERIE_NUIT_ACTIF,
                ActivityPeriod.forHours(1),
                DateUtils.asDate(LocalDate.of(2017, 11, 1))
        );
        final Activity nightShifts = new Activity(
                "Astreintes passives",
                TVA,
                TJM,
                ActivityType.SEMAINE_NUIT_PASSIF,
                ActivityPeriod.forDays(4),
                DateUtils.asDate(LocalDate.of(2017, 11, 30))
        );
        final Activity weekdnNightShifts = new Activity(
                "Astreintes passives weekend",
                TVA,
                TJM,
                ActivityType.FERIE_NUIT_PASSIF,
                ActivityPeriod.forDays(2),
                DateUtils.asDate(LocalDate.of(2017, 11, 30))
        );

        invoice.addEntry(normalDays);
        invoice.addEntry(firstNovember);
        invoice.addEntry(nightShiftFerie);
        invoice.addEntry(nightCallingShiftFerie);
        invoice.addEntry(nightShifts);
        invoice.addEntry(weekdnNightShifts);

        final TestActivitiesByTypeRenderer invoiceActivitiesByTypeRenderer = new TestActivitiesByTypeRenderer(invoice.getEntries());
        invoiceActivitiesByTypeRenderer.render();
        invoiceActivitiesByTypeRenderer.renderTotalAmounts();
    }

    public static void decemberInvoice() {
        final Invoice invoice = new Invoice(
                "Facture de Décembre",
                Month.DECEMBER,
                DateUtils.asDate(LocalDate.of(2017, 12, 31))
        );

        final double TVA = .2;
        final double TJM = 330;

        final Activity normalDays = new Activity(
                "Journées normales",
                TVA,
                TJM,
                ActivityType.SEMAINE_ACTIF,
                ActivityPeriod.forDays(20),
                DateUtils.asDate(LocalDate.of(2017, 12, 31))
        );
        final Activity heureSupp = new Activity(
                "Heure supp",
                TVA,
                TJM,
                ActivityType.HEURES_SUPPS,
                ActivityPeriod.forHours(1),
                DateUtils.asDate(LocalDate.of(2017, 12, 14))
        );
        final Activity heureSupp1 = new Activity(
                "Heure supp",
                TVA,
                TJM,
                ActivityType.HEURES_SUPPS,
                ActivityPeriod.forHours(1),
                DateUtils.asDate(LocalDate.of(2017, 12, 15))
        );
        final Activity nightShifts = new Activity(
                "Astreintes passives",
                TVA,
                TJM,
                ActivityType.SEMAINE_NUIT_PASSIF,
                ActivityPeriod.forDays(3),
                DateUtils.asDate(LocalDate.of(2017, 12, 31))
        );
        final Activity weekdnNightShifts = new Activity(
                "Astreintes passives weekend",
                TVA,
                TJM,
                ActivityType.FERIE_NUIT_PASSIF,
                ActivityPeriod.forDays(1),
                DateUtils.asDate(LocalDate.of(2017, 12, 31))
        );
        final Activity nightCallingShifts = new Activity(
                "Appel semaine",
                TVA,
                TJM,
                ActivityType.SEMAINE_NUIT_ACTIF,
                ActivityPeriod.forDays(1),
                DateUtils.asDate(LocalDate.of(2017, 12, 21))
        );

        invoice.addEntry(normalDays);
        invoice.addEntry(heureSupp);
        invoice.addEntry(heureSupp1);
        invoice.addEntry(nightShifts);
        invoice.addEntry(weekdnNightShifts);
        invoice.addEntry(nightCallingShifts);

        final TestActivitiesByTypeRenderer invoiceActivitiesByTypeRenderer = new TestActivitiesByTypeRenderer(invoice.getEntries());
        invoiceActivitiesByTypeRenderer.render();
        invoiceActivitiesByTypeRenderer.renderTotalAmounts();
    }
}
