package com.example.datareader;

import java.time.LocalDate;
import java.util.ArrayList;

import com.example.products.Product;

public abstract class Datareader implements IDatareader {
    public static final int productTypeWine = 1;
    public static final int productTypeCheese = 2;

    public static final int datareader_mode_code = 0;
    public static final int datareader_mode_csv  = 1;

	public static void addProduct(ArrayList<Product> products, Product product) {
		if (!product.discardNeeded(LocalDate.now())) {
			products.add(product);
		} else {
			System.out.println(product.getBezeichnung() + " wurde nicht hinzugefügt");
		}
	}
}
