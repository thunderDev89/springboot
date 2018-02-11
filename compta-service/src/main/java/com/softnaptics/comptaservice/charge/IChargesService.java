package com.softnaptics.comptaservice.charge;

import com.softnaptics.comptamodel.entries.charges.Charges;

import java.util.List;

public interface IChargesService {
    List<Charges> listAll();

    List<Charges> getForInvoice(int id);

    Charges create(Charges charges);

    Charges read(int id);

    Charges update(Charges charges);

    void delete(int id);
}
