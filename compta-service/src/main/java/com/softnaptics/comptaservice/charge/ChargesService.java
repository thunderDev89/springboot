package com.softnaptics.comptaservice.charge;

import com.softnaptics.comptamodel.entries.charges.Charges;
import com.softnaptics.comptarepository.ChargesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargesService implements IChargesService {
    private final ChargesRepository repository;

    @Autowired
    public ChargesService(ChargesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Charges> listAll() {
        return repository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Charges> getForInvoice(int id) {
        return repository.findAllByInvoiceIdOrderByIdDesc(id);
    }

    @Override
    public Charges create(Charges charges) {
        return repository.save(charges);
    }

    @Override
    public Charges read(int id) {
        return repository.findOne(id);
    }

    @Override
    public Charges update(Charges charges) {
        return repository.save(charges);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}
