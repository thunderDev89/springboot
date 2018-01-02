package com.softnaptics.comptamain;

import com.softnaptics.comptamodel.entries.activity.Activity;
import com.softnaptics.comptamodel.entries.activity.property.ActivityPeriod;
import com.softnaptics.comptamodel.entries.activity.property.ActivityType;
import com.softnaptics.comptamodel.entries.charges.Charges;
import com.softnaptics.comptamodel.entries.charges.ChargesFactory;
import com.softnaptics.comptamodel.entries.utils.DateUtils;
import com.softnaptics.comptamodel.invoice.Invoice;
import com.softnaptics.comptarepository.ActivityRepository;
import com.softnaptics.comptarepository.ChargesRepository;
import com.softnaptics.comptarepository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.softnaptics"})
@EnableJpaRepositories({ "com.softnaptics" })
@EntityScan({ "com.softnaptics" })
public class SpringbootApplication {

    @Autowired
    private InvoiceRepository invoicesRepo;

    @Autowired
    private ActivityRepository activitiesRepo;

    @Autowired
    private ChargesRepository chargesRepo;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringbootApplication.class, args);
    }

    @PostConstruct
    private void testQueries() {

        initProperInvoice();
        decemberInvoice();
    }

    private void testInitComptaObject() {
        Invoice invoice = addInvoice();
        addActivities(invoice);
        addCharges(invoice);

        invoice = invoicesRepo.findOne(invoice.getId());

        System.err.println("List of activities : "+invoice.getActivities());
        System.err.println("List of charges : "+invoice.getCharges());
    }

    private void initProperInvoice() {
        final double TVA = .2;
        final double TJM = 330;

        final Invoice invoice = new Invoice(
                "Facture de Décembre",
                Month.NOVEMBER,
                DateUtils.asDate(LocalDate.of(2017, 12, 31))
        );

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

        final Charges remuneration = ChargesFactory.createRemuneration(
                4000,
                DateUtils.asDate(LocalDate.of(2017, 8, 5))
        );
        final Charges chequesCesu = ChargesFactory.createExpenseReport(
                "Achat cheque cesu",
                .2,
                500,
                DateUtils.asDate(LocalDate.of(2017, 12, 13))
        );
        final Charges chequesCadeauClients = ChargesFactory.createExpenseReport(
                "Cartes cadeau clients",
                .2,
                600,
                DateUtils.asDate(LocalDate.of(2017, 12, 24))
        );

        invoice.addActivity(normalDays);
        invoice.addActivity(heureSupp);
        invoice.addActivity(heureSupp1);
        invoice.addCharge(remuneration);
        invoice.addCharge(chequesCesu);
        invoice.addCharge(chequesCadeauClients);

        invoicesRepo.save(invoice);
    }

    Invoice addInvoice() {
        Invoice invoice = new Invoice("Decembre", Month.DECEMBER, DateUtils.asDate(LocalDate.of(2017, 12, 31)));
        invoicesRepo.save(invoice);
        return invoice;
    }

    void addActivities(Invoice invoice) {
        Activity activity = new Activity(
                "Journées normales",
                .2,
                330,
                ActivityType.SEMAINE_ACTIF,
                ActivityPeriod.forDays(20),
                DateUtils.asDate(LocalDate.of(2017, 12, 31))
        );
        activity.setInvoice(invoice);
        activitiesRepo.save(activity);
    }

    void addCharges(Invoice invoice) {
        final Charges remuneration = ChargesFactory.createRemuneration(
                4500,
                DateUtils.asDate(LocalDate.of(2017, 8, 5))
        );
        remuneration.setInvoice(invoice);

        final Charges ndfTelPro = ChargesFactory.createExpenseReport(
                "Achat téléphone",
                .2,
                540.41,
                DateUtils.asDate(LocalDate.of(2017, 8, 15))
        );
        ndfTelPro.setInvoice(invoice);

        chargesRepo.save(Arrays.asList(remuneration, ndfTelPro));
    }

    void decemberInvoice() {
        final Invoice invoice = new Invoice(
                "Vraie Facture de Décembre",
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

        invoice.addActivity(normalDays);
        invoice.addActivity(heureSupp);
        invoice.addActivity(heureSupp1);
        invoice.addActivity(nightShifts);
        invoice.addActivity(weekdnNightShifts);
        invoice.addActivity(nightCallingShifts);

        invoicesRepo.save(invoice);
    }
}
