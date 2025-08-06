package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream input = new FileInputStream("src/test/resources/config.properties");
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file.", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}