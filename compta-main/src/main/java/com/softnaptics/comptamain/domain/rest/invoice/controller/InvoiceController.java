package com.softnaptics.comptamain.domain.rest.invoice.controller;

import com.softnaptics.comptamain.domain.rest.invoice.exception.InvoiceNotFoundException;
import com.softnaptics.comptamodel.invoice.Invoice;
import com.softnaptics.comptaservice.invoice.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private IInvoiceService invoiceService;

    @Autowired
    public InvoiceController(IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<InvoiceDTO> list() {
        final List<Invoice> invoices = invoiceService.listAll();
        return InvoiceDTO.fromEntities(invoices);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public InvoiceDTO create(@RequestBody InvoiceDTO invoice) {
        final Invoice createdInvoice = invoiceService.create(InvoiceDTO.toEntity(invoice));
        return InvoiceDTO.fromEntity(createdInvoice);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public InvoiceDTO read(@PathVariable(value = "id") int id) throws InvoiceNotFoundException {
        final Invoice invoice = invoiceService.read(id);
        if (invoice == null) {
            throw new InvoiceNotFoundException("Invoice with id: " + id + " not found.");
        }
        return InvoiceDTO.fromEntity(invoice);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public InvoiceDTO update(@RequestBody InvoiceDTO invoice) {
        final Invoice updatedInvoice = invoiceService.update(InvoiceDTO.toEntity(invoice));
        return InvoiceDTO.fromEntity(updatedInvoice);
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
