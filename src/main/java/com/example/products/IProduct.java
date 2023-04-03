package com.example.products;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IProduct {
    boolean discardNeeded(LocalDate date);
    int getQualitaet(LocalDate date);
    BigDecimal getTagesPreis(LocalDate date);
}
