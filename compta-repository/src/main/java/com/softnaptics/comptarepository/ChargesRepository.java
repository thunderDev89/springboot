package com.softnaptics.comptarepository;

import com.softnaptics.comptamodel.entries.charges.Charges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargesRepository extends JpaRepository<Charges, Integer> {
}
