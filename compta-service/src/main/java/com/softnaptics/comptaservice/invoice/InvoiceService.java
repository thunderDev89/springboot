package com.softnaptics.comptaservice.invoice;

import com.softnaptics.comptamodel.invoice.Invoice;
import com.softnaptics.comptarepository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class InvoiceService implements IInvoiceService {

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

    public List<Invoice> getAll() {
        return repository.findAll();
    }

    public Invoice get(int id) {
        return repository.findOne(id);
    }

    @Override
    public Invoice update(Invoice invoice) {
        return save(invoice);
    }

    /** NEW METHODS : OVERRIDED **/
    @Override
    public List<Invoice> listAll() {
        return repository.findAll();
    }

    @Override
    public Invoice create(Invoice invoice) {
        return repository.save(invoice);
    }

    @Override
    public Invoice read(int id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}
