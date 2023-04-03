package com.example.datareader;

import java.io.File;

import org.ini4j.Ini;

public class Inireader {
    

	public static int getDataMode() {
        String value = getIniValue("data", "mode");

        if (value.length() != 0) {
            return Integer.valueOf(value);
        } else {
            return 0;
        }
	}

	public static int getOutputMode() {
        String value = getIniValue("output", "mode");

        if (value.length() != 0) {
            return Integer.valueOf(value);
        } else {
            return 0;
        }
	}
	
	public static String getDataSource() {
		return getIniValue("data", "source");
	}

	public static String getIniValue(String file, String section, String key) {
		String value = "";
		try {
			File configFile = new File(file);
			Ini ini = new Ini(configFile);
			value = ini.get(section, key);
		} catch(Exception e) {
			System.out.println(e.getCause());
		}
		return value;
	}

	public static String getIniValue(String section, String key) {
		String value = "";
		try {
			File configFile = new File("config.ini");
			Ini ini = new Ini(configFile);
			value = ini.get(section, key);
		} catch(Exception e) {
			System.out.println(e.getCause());
		}
		return value;
	}
}
