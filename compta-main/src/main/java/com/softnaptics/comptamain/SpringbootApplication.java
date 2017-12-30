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
        final Invoice invoice = addInvoice();
        addActivities(invoice);
        addCharges(invoice);
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
}
