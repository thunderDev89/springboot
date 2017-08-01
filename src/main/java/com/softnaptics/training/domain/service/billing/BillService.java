package com.softnaptics.training.domain.service.billing;

import com.softnaptics.training.domain.billing.Bill;
import com.softnaptics.training.repository.billing.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    private BillRepository repository;

    @Autowired
    public BillService(BillRepository repository) {
        this.repository = repository;
    }

    public List<Bill> getAllBills() {
        return repository.getAllByOrderByIdDesc();
    }

    public List<Bill> getBillsOfMonth(int monthNumber) {
        return repository.getBillsByMonth(monthNumber);
    }
}
