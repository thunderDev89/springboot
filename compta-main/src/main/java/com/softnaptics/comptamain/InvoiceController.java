package com.softnaptics.comptamain;

import com.softnaptics.comptamain.invoice.model.InvoiceForm;
import com.softnaptics.comptamodel.invoice.Invoice;
import com.softnaptics.comptarenderer.invoice.InvoiceActivityAmountsByType;
import com.softnaptics.comptaservice.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute InvoiceForm invoice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "views/invoice/detail";
        }
        final Invoice updateInvoice = InvoiceForm.to(invoice);
        invoiceService.update(updateInvoice);

        return "redirect:/invoices";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String createNew(Model model) {
        model.addAttribute("pageTitle", "New Invoice");
        model.addAttribute("invoice", new InvoiceForm());

        return "views/invoice/detail";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute InvoiceForm invoice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "views/invoice/detail";
        }
        final Invoice updateInvoice = InvoiceForm.to(invoice);
        invoiceService.save(updateInvoice);

        return "redirect:/invoices";
    }

    @RequestMapping(path = "/{id}/activity/sumup")
    public String getActivitiesSummary(Model model, @PathVariable int id) {
        model.addAttribute("pageTitle", "Invoice's activities sumup");
        Invoice invoice = invoiceService.get(id);

        InvoiceActivityAmountsByType renderer = new InvoiceActivityAmountsByType(invoice.getActivities());
        model.addAttribute("amounts", renderer.getAmountsList());
        return "views/invoice/activity/sumup";
    }
}
