package com.softnaptics.comptarepository;

import com.softnaptics.comptamodel.entries.activity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    List<Activity> findAllByOrderByIdDesc();

    List<Activity> findAllByInvoiceIdOrderByIdDesc(int id);
}
