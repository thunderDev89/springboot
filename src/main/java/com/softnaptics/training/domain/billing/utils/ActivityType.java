package com.softnaptics.training.domain.billing.utils;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * @author Audrik !
 */
@Embeddable
public enum ActivityType {
    SEMAINE_ACTIF(BigDecimal.ONE),
    HEURE_SUPP(new BigDecimal(1.5)),
    WEEKEND_ACTIF(new BigDecimal(1.5)),
    FERIE_ACTIF(new BigDecimal(2)),
    SEMAINE_NUIT_PASSIF(new BigDecimal(.05)),
    SEMAINE_NUIT_ACTIF(new BigDecimal(1.5)),
    WEEKEND_NUIT_PASSIF(new BigDecimal(.2)),
    WEEKEND_NUIT_ACTIF(new BigDecimal(1.5));

    private final BigDecimal rate;
    private ActivityType(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
