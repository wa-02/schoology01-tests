package org.example.core.ui;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

public final class DriverFactory {

    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    private static List<WebDriver> storedDrivers = new ArrayList<>();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> storedDrivers.forEach(WebDriver::quit)));
    }

    private DriverFactory() {
    }

    public static void addDriver(final WebDriver driver) {
        drivers.set(driver);
        storedDrivers.add(driver);
    }

    public static WebDriver getDriver() {
        return drivers.get();
    }

}
