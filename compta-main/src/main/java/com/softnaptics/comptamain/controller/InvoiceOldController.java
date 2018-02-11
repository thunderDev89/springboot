package com.softnaptics.comptamain.controller;

import com.softnaptics.comptamain.invoice.model.InvoiceForm;
import com.softnaptics.comptamodel.invoice.Invoice;
import com.softnaptics.comptarenderer.invoice.gui.GuiInvoiceByActivity;
import com.softnaptics.comptarenderer.invoice.gui.GuiInvoiceByEntry;
import com.softnaptics.comptaservice.invoice.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.Month;
import java.util.List;

@Controller
@RequestMapping("/invoices")
public class InvoiceOldController {
    private InvoiceService invoiceService;

    @Autowired
    public InvoiceOldController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("pageTitle", "All invoices");
        List<Invoice> invoices = invoiceService.getAll();
        model.addAttribute("invoices", invoices);
        return "views/invoice/list";
    }

    @ModelAttribute("invoiceMonths")
    public Month[] populateMonthsList() {
        return Month.values();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String get(Model model, @PathVariable int id) {
        final Invoice invoice = invoiceService.get(id);

        model.addAttribute("pageTitle", invoice.getName());
        model.addAttribute("invoice", InvoiceForm.from(invoice));
        return "views/invoice/detail";
    }

    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute InvoiceForm invoice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "views/invoice/detail";
        }
        final Invoice updateInvoice = InvoiceForm.to(invoice);
        invoiceService.update(updateInvoice);

        return "redirect:/invoices";
    }

    //@Secured("ROLE_ADMIN")
    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String createNew(Model model) {
        model.addAttribute("pageTitle", "New Invoice");
        model.addAttribute("invoice", new InvoiceForm());

        return "views/invoice/detail";
    }

    //@Secured("ROLE_ADMIN")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute InvoiceForm invoice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "views/invoice/detail";
        }
        final Invoice updateInvoice = InvoiceForm.to(invoice);
        invoiceService.save(updateInvoice);

        return "redirect:/invoices";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(path = "/{id}/activity/sumup")
    public String getActivitiesSummary(Model model, @PathVariable int id) {
        model.addAttribute("pageTitle", "Invoice's activities sumup");
        Invoice invoice = invoiceService.get(id);

        //InvoiceActivityAmountsByType renderer = new InvoiceActivityAmountsByType(invoice.getActivities());
        GuiInvoiceByActivity renderer = new GuiInvoiceByActivity(invoice.getActivities());
        model.addAttribute("amounts", renderer.getAmountsList());
        return "views/invoice/activity/sumup";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(path = "/{id}/entry/sumup")
    public String getEntriesSummary(Model model, @PathVariable int id) {
        model.addAttribute("pageTitle", "Invoice's entries sumup");
        Invoice invoice = invoiceService.get(id);

        GuiInvoiceByEntry renderer = new GuiInvoiceByEntry(invoice.getEntries());
        model.addAttribute("amounts", renderer.getAmountsList());
        return "views/invoice/entry/sumup";
    }
}
