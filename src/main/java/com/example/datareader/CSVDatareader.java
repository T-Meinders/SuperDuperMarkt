package com.example.datareader;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.products.*;

public class CSVDatareader extends Datareader {
    String filename;

    public static final int csv_field_productType   = 0;
    public static final int csv_field_dateAdded     = 1;
    public static final int csv_field_description   = 2;
    public static final int csv_field_quality       = 3;
    public static final int csv_field_price         = 4;
    public static final int csv_field_dateExpired   = 5;

    public CSVDatareader(String filename) {
        this.setFilename(filename);
    }

    public void readData(ArrayList<Product> products) {
        String line;
        String[] linevalues;

        try {
            Scanner scanner = new Scanner(new File(this.getFilename()), "UTF-8");

            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                linevalues = line.split(";");

                switch(Integer.parseInt(linevalues[csv_field_productType])) {
                    case productTypeWine:
                        addProduct(products, new Wine  (linevalues[csv_field_dateAdded], linevalues[csv_field_description], Integer.parseInt(linevalues[csv_field_quality]) , new BigDecimal(linevalues[csv_field_price])));
                        break;
                    case productTypeCheese:
                        addProduct(products, new Cheese(linevalues[csv_field_dateAdded], linevalues[csv_field_description], Integer.parseInt(linevalues[csv_field_quality]) , new BigDecimal(linevalues[csv_field_price]), linevalues[csv_field_dateExpired]));
                        break;
                }
            }
        } catch(Exception e) {
            System.out.println("Lesen der Datei " + this.getFilename() + " fehlgeschlagen.\n" + e);
        }
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return this.filename;
    }
}
