package com.softnaptics.training.repository.billing;

import com.softnaptics.training.domain.billing.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Audrik !
 */
public interface BillRepository extends CrudRepository<Bill, Long> {

    List<Bill> getAllByOrderByIdDesc();

    @Query("select b from Bill b where month(b.refDate) = ?1")
    List<Bill> getBillsByMonth(int monthNumber);
}
