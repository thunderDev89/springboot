package com.softnaptics.training.domain.billing;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;

/**
 * Represente les factures d'achat de tout ordre (restau, materiel, etc)
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Bill {
    @Id
    @GeneratedValue
    private Long id;

    private @Getter @Setter
    @NonNull
    String name;

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP")
    private @Getter @Setter @NonNull
    Date refDate;

    private @Getter @Setter @NonNull
    BigDecimal amount;

    private @Getter @Setter BillStatus status = BillStatus.PEND;

    public static enum BillStatus {
        PEND, VALI
    }

    public static void main(String[] args) {
        BigDecimal amnt = new BigDecimal(0.0005, new MathContext(1, RoundingMode.HALF_UP));
        System.out.println("Value="+amnt.toPlainString()+" - "+amnt.doubleValue());
        System.out.println("scale=" + amnt.scale());
        System.out.println("Precision=" + amnt.precision());
    }
}
