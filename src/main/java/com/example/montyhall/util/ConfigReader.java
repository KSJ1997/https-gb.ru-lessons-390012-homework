package com.example.montyhall.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    public static int readTotalGames() {
        Properties properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return 0;
            }

            properties.load(input);
            return Integer.parseInt(properties.getProperty("totalGames", "1000"));
        } catch (IOException e) {
            e.printStackTrace();
            return 0; 
        }
    }
}
