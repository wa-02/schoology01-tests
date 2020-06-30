package org.example.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class Internationalization {

    private static final Internationalization INSTANCE = new Internationalization();

    private Properties properties;

    private Internationalization() {
        String language = String.format("src/test/resources/i18n/Variables_%s.properties",
                Environment.getInstance().getValue("language"));
        try (FileInputStream fileInputStream = new FileInputStream(language)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Internationalization getInstance() {
        return INSTANCE;
    }

    public String getValue(final String value) {
        return properties.getProperty(value);
    }

}
