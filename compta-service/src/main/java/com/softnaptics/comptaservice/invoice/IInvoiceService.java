package com.softnaptics.comptaservice.invoice;

import com.softnaptics.comptamodel.invoice.Invoice;

import java.util.List;

public interface IInvoiceService {
    List<Invoice> listAll();

    Invoice create(Invoice invoice);

    Invoice read(int id);

    Invoice update(Invoice invoice);

    void delete(int id);
}
