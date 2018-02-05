package com.softnaptics.comptamain.domain.rest.invoice.controller;

import com.softnaptics.comptamain.domain.rest.invoice.exception.InvoiceNotFoundException;
import com.softnaptics.comptamodel.invoice.Invoice;
import com.softnaptics.comptaservice.invoice.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/invoices")
public class HomeController {

    private IInvoiceService invoiceService;

    @Autowired
    public HomeController(IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Invoice> list() {
        return invoiceService.listAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Invoice create(@RequestBody Invoice invoice) {
        return invoiceService.create(invoice);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Invoice read(@PathVariable(value = "id") int id) throws InvoiceNotFoundException {
        final Invoice invoice = invoiceService.read(id);
        if (invoice == null) {
            throw new InvoiceNotFoundException("Invoice with id: " + id + " not found.");
        }
        return invoice;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Invoice update(@RequestBody Invoice invoice) {
        return invoiceService.update(invoice);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") int id) {
        invoiceService.delete(id);
    }

    @ExceptionHandler(InvoiceNotFoundException.class)
    public void handleInvoiceNotFoundException(InvoiceNotFoundException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
