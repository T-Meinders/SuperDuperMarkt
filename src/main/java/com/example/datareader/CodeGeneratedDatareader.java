package com.example.datareader;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.example.products.*;

public class CodeGeneratedDatareader extends Datareader {
    public void readData(ArrayList<Product> products) {

        addProduct(products, new Wine  ("2023-03-07", "Rotwein"     , 47 , BigDecimal.valueOf(12.3)));
        addProduct(products, new Cheese("2023-03-07", "Hartkäse"    , 90 , BigDecimal.valueOf(12.3), "2023-04-23"));
        addProduct(products, new Wine  ("2023-01-09", "Weißwein"    , 4  , BigDecimal.valueOf(10.2)));
        addProduct(products, new Cheese("2023-04-01", "Weichkäse"   , 70 , BigDecimal.valueOf(12.3), "2023-06-30"));
        addProduct(products, new Cheese("2022-12-31", "Schnittkäse" , 70 , BigDecimal.valueOf(12.3), "2023-04-03"));
        addProduct(products, new Cheese("2023-02-01", "Frischkäse"  , 130, BigDecimal.valueOf(12.3), "2023-05-28"));
    }
}
