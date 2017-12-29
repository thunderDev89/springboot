package com.softnaptics.comptamodel.entries.activity.property;

public enum ActivityType {
    SEMAINE_ACTIF(1),
    HEURES_SUPPS(1.5),
    SAMEDI_ACTIF(1.5),
    FERIE_ACTIF(2),
    SEMAINE_NUIT_PASSIF(.05),
    SEMAINE_NUIT_ACTIF(1.5),
    FERIE_NUIT_PASSIF(.2),
    FERIE_NUIT_ACTIF(2)
    ;

    private final double rate;

    ActivityType(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
