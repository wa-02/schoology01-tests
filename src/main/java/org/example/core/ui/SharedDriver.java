package org.example.core.ui;

import org.example.core.Environment;
import org.openqa.selenium.WebDriver;

public class SharedDriver {

    public SharedDriver() {
        if (DriverFactory.getDriver() == null) {
            WebDriver driver = BrowserFactory.getBrowser(Environment.getInstance().getValue("browser"));
            DriverFactory.addDriver(driver);
        }
    }
}
