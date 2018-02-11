package com.softnaptics.comptarepository;

import com.softnaptics.comptamodel.entries.charges.Charges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargesRepository extends JpaRepository<Charges, Integer> {
    List<Charges> findAllByOrderByIdDesc();

    List<Charges> findAllByInvoiceIdOrderByIdDesc(int id);
}
