package com.example.products;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.math.BigDecimal;

public class Cheese extends Product {

    public Cheese(String dateAdded, String description, int quality, BigDecimal price, String dateExpiring) {
        super(dateAdded, description, quality, price);
        this.verfallsdatum = LocalDate.parse(dateExpiring);
    }

    public boolean discardNeeded(LocalDate date) {
        if (this.getQualitaet(date) < 30 || date.compareTo(this.verfallsdatum) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getQualitaet(LocalDate futureDate) {
        long dayDifference;
        dayDifference = ChronoUnit.DAYS.between(this.erfassungsdatum, futureDate);
        return (this.qualitaet - ((int)dayDifference));
    }

    public BigDecimal getTagesPreis(LocalDate date) {
        return this.getPreis().add(BigDecimal.valueOf(0.1).multiply(BigDecimal.valueOf(this.getQualitaet(date))));
    }
    
}
