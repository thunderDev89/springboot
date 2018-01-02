package com.softnaptics.comptamain;

import com.softnaptics.comptamodel.invoice.Invoice;
import com.softnaptics.comptarenderer.invoice.InvoiceActivityAmountsByType;
import com.softnaptics.comptaservice.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {
    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("pageTitle", "All invoices");
        List<Invoice> invoices = invoiceService.getAll();
        model.addAttribute("invoices", invoices);
        return "views/invoice/list";
    }

    @RequestMapping(path = "/{id}/activity/sumup")
    public String geteActivitiesSummary(Model model, @PathVariable int id) {
        model.addAttribute("pageTitle", "Invoice's activities sumup");
        Invoice invoice = invoiceService.get(id);

        InvoiceActivityAmountsByType renderer = new InvoiceActivityAmountsByType(invoice.getActivities());
        model.addAttribute("amounts", renderer.getAmountsList());
        return "views/invoice/activity/sumup";
    }
}
