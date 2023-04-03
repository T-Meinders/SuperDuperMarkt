package com.example.superDuperMarkt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.datareader.*;
import com.example.products.Product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@SpringBootApplication
public class SuperDuperMarktApplication {

	public static int outputModeNeeded = 0;
	public static int outputModeFull   = 1;

	public static int outputLengthDescription 	= 15;
	public static int outputLengthDateAdded 	= 18;
	public static int outputLengthBasePrice 	= 12;
	public static int outputLengthQuality   	= 11;
	public static int outputLengthCurrentPrice	= 9;
	public static int outputLengthDateExpiring	= 16;
	public static int outputLengthDaysExpiring	= 7;
	public static int outputLengthDiscard		= 13;

	public static void main(String[] args) {
		Datareader datareader;
		ArrayList<Product> products = new ArrayList<Product>();
		SpringApplication.run(SuperDuperMarktApplication.class, args);
		System.out.println("Ausgabe der Produkte");

		if (Inireader.getDataMode() == Datareader.datareader_mode_code) {
			datareader = new CodeGeneratedDatareader();
		} else {
			datareader = new CSVDatareader(Inireader.getDataSource());
		}

		// Muss die Qualität von Käse zwischen 80 und 130 liegen oder ist die Qualität unabhängig vom Verfallsdatum?

		datareader.readData(products);
		

		if (products.size() > 0) {
			if (Inireader.getOutputMode() == outputModeNeeded) {
				neededOutput(products);
			} else {
				fullOutput(products);
			}
		}
	}

	private static void fullOutput(ArrayList<Product> products) {
		Product product;

		
		for(int iDays = 0; iDays <= 50; iDays++) {
			System.out.println("Tag " + iDays);
		
			System.out.format("%-"+outputLengthDescription+"s", "Bezeichnung");
			System.out.format("%"+outputLengthDateAdded+"s", "Erfassungsdatum");
			System.out.format("%"+outputLengthBasePrice+"s", "Grundpreis");
			System.out.format("%"+outputLengthQuality+"s", "Qualität");
			System.out.format("%"+outputLengthCurrentPrice+"s", "Preis");
			System.out.format("%"+outputLengthDateExpiring+"s", "Verfallsdatum");
			System.out.format("%"+outputLengthDaysExpiring+"s", "Tage");
			System.out.println();

			// Da wir zwischendurch Produkte entfernen könnten, laufen wir von oben nach unten durch, damit nichts übersprungen wird
			for(int iProducts = products.size() - 1; iProducts >= 0; iProducts--) {
				product = products.get(iProducts);
	
				if (!product.discardNeeded(LocalDate.now().plusDays(iDays))) {
					System.out.format("%-"+outputLengthDescription+"s", product.getBezeichnung());
					System.out.format("%"+outputLengthDateAdded+"s", product.getErfassungsdatum().format(DateTimeFormatter.ofPattern("dd.MM.uuuu")));
					System.out.format("%"+outputLengthBasePrice+"s", product.getPreis().toString());
					System.out.format("%"+outputLengthQuality+"s", Integer.toString(product.getQualitaet(LocalDate.now().plusDays(iDays))));
					System.out.format("%"+outputLengthCurrentPrice+"s", product.getTagesPreis(LocalDate.now().plusDays(iDays)).toString());
					if (product.getVerfallsdatum() != null) {
						System.out.format("%"+outputLengthDateExpiring+"s", product.getVerfallsdatum().format(DateTimeFormatter.ofPattern("dd.MM.uuuu")));
						System.out.format("%"+outputLengthDaysExpiring+"s", Integer.toString((int)ChronoUnit.DAYS.between(LocalDate.now().plusDays(iDays), product.verfallsdatum)));
					}
					System.out.println();
				} else {
					System.out.println(product.getBezeichnung() + " muss entfernt werden");
				}
			}
			System.out.println("\n");
		}

	}

	private static void neededOutput(ArrayList<Product> products) {
		Product product;

		
		for(int iDays = 0; iDays <= 50; iDays++) {
			System.out.println("Tag " + iDays);
			
			System.out.format("%-"+outputLengthDescription+"s", "Name");
			System.out.format("%"+outputLengthCurrentPrice+"s", "Preis");
			System.out.format("%"+outputLengthQuality+"s", "Qualität");
			System.out.format("%"+outputLengthDiscard+"s", "Entsorgung");
			System.out.println();

			// Da wir zwischendurch Produkte entfernen könnten, laufen wir von oben nach unten durch, damit nichts übersprungen wird
			for(int iProducts = products.size() - 1; iProducts >= 0; iProducts--) {
				product = products.get(iProducts);
	
				System.out.format("%-"+outputLengthDescription+"s", product.getBezeichnung());
				System.out.format("%"+outputLengthCurrentPrice+"s", product.getTagesPreis(LocalDate.now().plusDays(iDays)).toString());
				System.out.format("%"+outputLengthQuality+"s", Integer.toString(product.getQualitaet(LocalDate.now().plusDays(iDays))));
				
				if (product.discardNeeded(LocalDate.now().plusDays(iDays))) {
					System.out.format("%"+outputLengthDiscard+"s", "Ja");
				} else {
					System.out.format("%"+outputLengthDiscard+"s", "Nein");
				}
				System.out.println("");
			}
			System.out.println("\n");
		}

	}
}
