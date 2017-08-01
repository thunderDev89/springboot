package com.softnaptics.training.controller.billing;

import com.softnaptics.training.domain.service.billing.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/bills")
public class BillController {
    private static final String PARAM_PAGE_TITLE = "pageTitle";
    private static final String PARAMS_BILLS = "bills";
    private BillService service;

    @Autowired
    public BillController(BillService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listAll(Model model) {
        model.addAttribute(PARAM_PAGE_TITLE, "Liste des factures");
        model.addAttribute(PARAMS_BILLS, service.getAllBills());
        return "views/bills/list";
    }

    @RequestMapping(path = "/{monthNumber}", method = RequestMethod.GET)
    public String listForMonth(@PathVariable(name = "monthNumber") int monthNumber, Model model) {
        model.addAttribute(PARAM_PAGE_TITLE, "Liste des factures du mois "+monthNumber);
        model.addAttribute(PARAMS_BILLS, service.getBillsOfMonth(monthNumber));
        return "views/bills/list";
    }
}
