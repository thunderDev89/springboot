package com.softnaptics.comptaservice;

import com.softnaptics.comptamodel.invoice.Invoice;
import com.softnaptics.comptarepository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class InvoiceService {

    private final InvoiceRepository repository;

    @Autowired
    public InvoiceService(InvoiceRepository repository) {
        this.repository = repository;
    }

    public Invoice save(Invoice invoice) {
        return repository.save(invoice);
    }

    public Collection<Invoice> saveAll(Collection<Invoice> invoices) {
        return repository.save(invoices);
    }
}
