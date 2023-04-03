package com.example.products;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Product implements IProduct{
    protected LocalDate erfassungsdatum;
    public String bezeichnung;
    public int qualitaet;
    public LocalDate verfallsdatum;
    public BigDecimal preis;

    public Product() {
    }

    public Product(String dateAdded, String description, int quality, BigDecimal price) {
        this.erfassungsdatum = LocalDate.parse(dateAdded);
        this.bezeichnung     = description;
        this.qualitaet       = quality;
        this.preis           = price;
    }


    public String getBezeichnung() {
        return bezeichnung;
    }
    public LocalDate getVerfallsdatum() {
        return verfallsdatum;
    }
    public BigDecimal getPreis() {
        return this.preis;
    }
    public LocalDate getErfassungsdatum() {
        return erfassungsdatum;
    }


    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
    public void setQualitaet(int qualitaet) {
        this.qualitaet = qualitaet;
    }
    public void setVerfallsdatum(LocalDate verfallsdatum) {
        this.verfallsdatum = verfallsdatum;
    }
    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }
}
