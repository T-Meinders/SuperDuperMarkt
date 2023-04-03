package com.example.products;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.math.BigDecimal;

public class Wine extends Product {

    public Wine(String dateAdded, String description, int quality, BigDecimal price) {
        super(dateAdded, description, quality, price);
    }

    public int getQualitaet(LocalDate date) {
        long dayDifference;
        int currentQuality;
        dayDifference = ChronoUnit.DAYS.between(this.erfassungsdatum, date);
        // Es wird immer abgerundet, damit nur volle 10 Tage die Qualität erhöhen
        currentQuality = this.qualitaet + ((int)Math.floor(dayDifference / 10));
        // Qualität ist bei 50 gedeckelt, somit geben wir nur die ausgerechnete Qualität zurück, solange diese unter 50 liegt
        return Math.min(50, currentQuality);
    }

    public boolean discardNeeded(LocalDate date) {
        if (this.getQualitaet(date) < 0) {
            return true;
        } else {
            return false;
        }
    }

    public BigDecimal getTagesPreis(LocalDate date) {
        return this.getPreis();
    }
}
