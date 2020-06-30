package org.example.core.ui;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    public static final int DEFAULT_IMPLICIT_TIMEOUT = 15;
    public static final int EXPLICIT_WAIT_IN_SECONDS = 20;

    protected WebDriver driver;

    protected WebDriverWait wait;

    protected WebdriverAction action;

    public AbstractPage() {
        this.driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        this.wait = new WebDriverWait(driver, EXPLICIT_WAIT_IN_SECONDS);
        this.action = new WebdriverAction(driver, wait);
        PageFactory.initElements(driver, this);
    }
}
