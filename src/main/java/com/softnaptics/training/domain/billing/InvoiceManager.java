package com.softnaptics.training.domain.billing;

import com.softnaptics.training.domain.billing.rendering.BasicStreamUsingRenderer;
import com.softnaptics.training.domain.billing.rendering.BasicTotalAmount2Renderer;
import com.softnaptics.training.domain.billing.utils.ActivityType;
import com.softnaptics.training.domain.billing.utils.Period;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Audrik !
 */
public class InvoiceManager {

    public static void juneInvoice() {
        final Invoice factureJuin = new Invoice("Facture de Juin", 6);

        final Activity normalActivity = new Activity(
                "Jours normaux travaillés",
                ActivityType.SEMAINE_ACTIF,
                Date.from(LocalDate.of(2017, 6, 30).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Period.forDays(21));

        final Activity astreinteWeeknd = new Activity(
                "Astreinte Samedi",
                ActivityType.WEEKEND_ACTIF,
                Date.from(LocalDate.of(2017, 6, 5).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Period.forHours(10)
        );

        final Activity astreinteNuit = new Activity(
                "Astreinte nuit",
                ActivityType.SEMAINE_NUIT_PASSIF,
                Date.from(LocalDate.of(2017, 6, 30).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Period.forDays(9)
        );

        final Activity astreinteNuitActif = new Activity(
                "Astreinte nuit active",
                ActivityType.SEMAINE_NUIT_ACTIF,
                Date.from(LocalDate.of(2017, 6, 5).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Period.forHours(2)
        );

        final Activity astreinteNuitWeeknd = new Activity(
                "Astreinte nuit weekend",
                ActivityType.WEEKEND_NUIT_PASSIF,
                Date.from(LocalDate.of(2017, 6, 5).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Period.forDays(1)
        );

        factureJuin.addActivity(normalActivity);
        factureJuin.addActivity(astreinteWeeknd);
        factureJuin.addActivity(astreinteNuit);
        factureJuin.addActivity(astreinteNuitActif);
        factureJuin.addActivity(astreinteNuitWeeknd);

        final BasicTotalAmount2Renderer byTypeRendering = new BasicTotalAmount2Renderer();
        factureJuin.accept(byTypeRendering);
        byTypeRendering.render();

        factureJuin.print();
    }

    public static void julyInvoice() {
        final Invoice factureJuillet = new Invoice("Facture de Juillet", 7);

        final Activity normalActivity = new Activity(
                "Heures normales",
                ActivityType.SEMAINE_ACTIF,
                Date.from(LocalDate.of(2017, 7, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Period.forDays(20)
        );

        final Activity astreinteWeekend = new Activity(
                "Astreinte samedi",
                ActivityType.WEEKEND_ACTIF,
                Date.from(LocalDate.of(2017, 7, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Period.forHours(9)
        );

        final Activity astreinteFerie = new Activity(
                "Astreinte 14 juillet",
                ActivityType.FERIE_ACTIF,
                Date.from(LocalDate.of(2017, 7, 14).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Period.forHours(10)
        );

        final Activity astreinteNuit = new Activity(
                "Astreintes nuit",
                ActivityType.SEMAINE_NUIT_PASSIF,
                Date.from(LocalDate.of(2017, 7, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Period.forDays(3)
        );

        final Activity astreinteNuitWeekendFerie = new Activity(
                "Astreintes weekend nuit",
                ActivityType.WEEKEND_NUIT_PASSIF,
                Date.from(LocalDate.of(2017, 7, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Period.forDays(4)
        );

        final Activity astreinteNuitActive = new Activity(
                "Heure supplémentaire",
                ActivityType.WEEKEND_NUIT_ACTIF,
                Date.from(LocalDate.of(2017, 7, 29).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Period.forHours(1)
        );

        final Activity heureSupp = new Activity(
                "Heure supplémentaire",
                ActivityType.HEURE_SUPP,
                Date.from(LocalDate.of(2017, 7, 28).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Period.forHours(1)
        );

        factureJuillet.addActivity(normalActivity);
        factureJuillet.addActivity(astreinteWeekend);
        factureJuillet.addActivity(astreinteFerie);
        factureJuillet.addActivity(astreinteNuit);
        factureJuillet.addActivity(astreinteNuitWeekendFerie);
        factureJuillet.addActivity(astreinteNuitActive);
        factureJuillet.addActivity(heureSupp);

        BasicStreamUsingRenderer renderer = new BasicStreamUsingRenderer();
        factureJuillet.accept(renderer);
        renderer.render();

        final BasicTotalAmount2Renderer renderer2 = new BasicTotalAmount2Renderer();
        factureJuillet.accept(renderer2);
        renderer2.render();

//        factureJuillet.print();
    }

    private static void octoberInvoice() {
        final Invoice octoberInvoice = new Invoice("Facture Octobre", 9);

        final Activity normal = new Activity("Jours normaux",
                ActivityType.SEMAINE_ACTIF,
                Date.from(LocalDate.of(2017, 10, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Period.forDays(21.5)
        );
        final Activity nightShifts = new Activity(
                "Astreintes passives",
                ActivityType.SEMAINE_NUIT_PASSIF,
                Date.from(LocalDate.of(2017, 10, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Period.forDays(5)
        );
        final Activity nightCallingShift = new Activity(
                "Nuit appel semaine",
                ActivityType.SEMAINE_NUIT_ACTIF,
                Date.from(LocalDate.of(2017, 10, 14).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Period.forHours(2)
        );
        final Activity weekndNightShift = new Activity(
                "Shift de nuit weekend",
                ActivityType.WEEKEND_NUIT_PASSIF,
                Date.from(LocalDate.of(2017, 10, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Period.forDays(1)
        );
        final Activity heuresSupps = new Activity(
                "Heures supps",
                ActivityType.HEURE_SUPP,
                Date.from(LocalDate.of(2017, 10, 17).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Period.forHours(2)
        );

        octoberInvoice.addActivity(normal);
        octoberInvoice.addActivity(nightShifts);
        octoberInvoice.addActivity(nightCallingShift);
        octoberInvoice.addActivity(heuresSupps);
        octoberInvoice.addActivity(weekndNightShift);

        final BasicStreamUsingRenderer renderer = new BasicStreamUsingRenderer();
        octoberInvoice.accept(renderer);
        renderer.render();

        final BasicTotalAmount2Renderer renderer2 = new BasicTotalAmount2Renderer();
        octoberInvoice.accept(renderer2);
        renderer2.render();

        // octoberInvoice.print();
    }

    public static void main(String[] args) {
        julyInvoice();
    }
}
