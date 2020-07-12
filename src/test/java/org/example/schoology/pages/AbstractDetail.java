package org.example.schoology.pages;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractDetail extends AbstractPage {

    @FindBy(css = ".enrollment-code")
    private WebElement accessCode;

    @FindBy(css = "#edit-submit")
    protected WebElement submitButton;

    public String getAccessCode() {
        return accessCode.getText();
    }


}