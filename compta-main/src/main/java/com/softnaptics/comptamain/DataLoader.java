package com.softnaptics.comptamain;

import com.google.common.collect.Sets;
import com.softnaptics.comptamodel.entries.activity.Activity;
import com.softnaptics.comptamodel.entries.activity.property.ActivityPeriod;
import com.softnaptics.comptamodel.entries.activity.property.ActivityType;
import com.softnaptics.comptamodel.entries.charges.Charges;
import com.softnaptics.comptamodel.entries.charges.ChargesFactory;
import com.softnaptics.comptamodel.entries.utils.DateUtils;
import com.softnaptics.comptamodel.invoice.Invoice;
import com.softnaptics.comptamodel.user.Role;
import com.softnaptics.comptamodel.user.User;
import com.softnaptics.comptarepository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;

@Component
public class DataLoader {

    private UserRepository userRepo;

    private RoleRepository roleRepo;

    private InvoiceRepository invoicesRepo;

    private ActivityRepository activitiesRepo;

    private ChargesRepository chargesRepo;

    @Autowired
    public DataLoader(RoleRepository roleRepo, UserRepository userRepo, InvoiceRepository invoicesRepo, ActivityRepository activitiesRepo, ChargesRepository chargesRepo) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.invoicesRepo = invoicesRepo;
        this.activitiesRepo = activitiesRepo;
        this.chargesRepo = chargesRepo;
    }

    @PostConstruct
    private void loadData() {
        initProperInvoice();
        initProperInvoice2();

        loadUsersAndRoles();
        checkUSersAndRoles();
    }

    private void checkUSersAndRoles() {
        Iterable<User> users = userRepo.findAll();
    }

    private void loadUsersAndRoles() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        adminRole = roleRepo.save(adminRole);
        userRole = roleRepo.save(userRole);


        final User admin = new User("masterkey", "pass", "aud");
        admin.setRoles(Sets.newHashSet(adminRole, userRole));

        final User user = new User("homeless", "pass", "user");
        user.setRoles(Sets.newHashSet(userRole));

        userRepo.save(admin);
        userRepo.save(user);
    }

    private void initProperInvoice() {
        final double TVA = .2;
        final double TJM = 330;

        final Invoice invoice = new Invoice(
                "Facture de Décembre",
                Month.DECEMBER,
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
        final Charges chequesCesu2 = ChargesFactory.createExpenseReport(
                "Achat cheque cesu",
                .2,
                1200,
                DateUtils.asDate(LocalDate.of(2017, 12, 31))
        );
        final Charges chequesCadeauClients = ChargesFactory.createExpenseReport(
                "Cartes cadeau clients",
                .2,
                600,
                DateUtils.asDate(LocalDate.of(2017, 12, 24))
        );
        final Charges loyers = ChargesFactory.createExpenseReport(
                "Refacturation Loyers",
                0,
                2502.11,
                DateUtils.asDate(LocalDate.of(2017, 12, 31))
        );
        final Charges factureMobile = ChargesFactory.createExpenseReport(
                "Facture mobile",
                .2,
                27.14,
                DateUtils.asDate(LocalDate.of(2017, 12, 29))
        );
        final Charges facturesRestau = ChargesFactory.createRestaurantCharge(
                199,
                DateUtils.asDate(LocalDate.of(2017, 12, 2))
        );
        final Charges facturesRestau2 = ChargesFactory.createRestaurantCharge(
                57,
                DateUtils.asDate(LocalDate.of(2017, 12, 16))
        );
        final Charges facturesRestau3 = ChargesFactory.createRestaurantCharge(
                96.5,
                DateUtils.asDate(LocalDate.of(2017, 12, 12))
        );

        invoice.addActivity(normalDays);
        invoice.addActivity(heureSupp);
        invoice.addActivity(heureSupp1);
        invoice.addCharge(remuneration);
        //invoice.addCharge(chequesCesu);
        //invoice.addCharge(chequesCesu2);
        //invoice.addCharge(chequesCadeauClients);
        invoice.addCharge(loyers);
        invoice.addCharge(factureMobile);
        invoice.addCharge(facturesRestau);
        invoice.addCharge(facturesRestau2);
        invoice.addCharge(facturesRestau3);

        invoicesRepo.save(invoice);
    }

    private void initProperInvoice2() {
        final Invoice julyInvoice = new Invoice(
                "Facture Juillet",
                Month.JULY,
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
        final Charges remuneration = ChargesFactory.createRemuneration(
                4500,
                DateUtils.asDate(LocalDate.of(2017, 8, 5))
        );

        final Charges ndfTelPro = ChargesFactory.createExpenseReport(
                "Achat téléphone",
                .2,
                540.41,
                DateUtils.asDate(LocalDate.of(2017, 8, 15))
        );

        julyInvoice.addActivity(normalDays);
        julyInvoice.addActivity(astreinteWeeknd);
        julyInvoice.addActivity(astreinteFerie);
        julyInvoice.addActivity(nightShifts);
        julyInvoice.addActivity(weekndNightShift);
        julyInvoice.addActivity(nightCallingShifts);
        julyInvoice.addActivity(suppHours);
        julyInvoice.addCharge(remuneration);
        julyInvoice.addCharge(ndfTelPro);

        invoicesRepo.save(julyInvoice);
    }
}
