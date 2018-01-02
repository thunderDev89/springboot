package com.softnaptics.comptacontroller;

import com.softnaptics.comptamodel.invoice.Invoice;
import com.softnaptics.comptarenderer.invoice.InvoiceEntryByTypeRenderer;
import com.softnaptics.comptarepository.ActivityRepository;
import com.softnaptics.comptarepository.ChargesRepository;
import com.softnaptics.comptarepository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HomeController {

    @Autowired
    private InvoiceRepository invoiceRepo;
    @Autowired
    private ActivityRepository activityRepo;
    @Autowired
    private ChargesRepository chargesRepo;

    public String getInvoiceResults() {
        final Invoice invoice = invoiceRepo.getOne(1);

        final InvoiceEntryByTypeRenderer renderer = new InvoiceEntryByTypeRenderer(invoice.getEntries());
        return renderer.getResults();
    }

    @RequestMapping("/{id}")
    public String getInvoiceResultsById(@PathVariable int id) {
        final Invoice invoice = invoiceRepo.getOne(id);
        invoice.getCharges();
        invoice.getActivities();
        final InvoiceEntryByTypeRenderer renderer = new InvoiceEntryByTypeRenderer(invoice.getEntries());
        return renderer.getResults();
    }
}
