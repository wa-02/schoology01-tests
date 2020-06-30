package org.example.schoology.pages;

import java.util.concurrent.TimeUnit;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login extends AbstractPage {

    // This info should come from config file.
    public static final int DEFAULT_IMPLICIT_TIMEOUT = 15;
    public static final int MIN_IMPLICIT_TIMEOUT = 3;

    @FindBy(css = "#edit-mail")
    private WebElement usernameTextField;

    @FindBy(css = "#edit-pass")
    private WebElement passwordTextField;

    @FindBy(css = "#edit-submit")
    private WebElement loginButton;

    @FindBy(css = "#confirmation_cancel")
    private WebElement cancelVerifyYourAccountButton;

    public Login() {
        PageFactory.initElements(driver, this);
    }


    // Todo
    public Home loginAs(final String username, final String pass) {
        driver.manage().deleteAllCookies();
        driver.get("https://app.schoology.com/login");
        usernameTextField.sendKeys(username);
        passwordTextField.sendKeys(pass);
        loginButton.click();
        verifyYourAccount();
        return new Home();
    }

    private void verifyYourAccount() {
        try {
            // Changing timeout
            driver.manage().timeouts().implicitlyWait(MIN_IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
            cancelVerifyYourAccountButton.click();
        } catch (NoSuchElementException e) {
            // nothing.
        } finally {
            // Restore timeout
            driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        }
    }

}
