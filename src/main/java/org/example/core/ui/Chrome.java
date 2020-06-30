package org.example.core.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome extends AbstractBrowser {
    @Override
    WebDriver init() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
