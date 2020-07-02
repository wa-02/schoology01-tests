package org.example.schoology.pages;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractDetail extends AbstractPage {

    @FindBy(css = "span[class='enrollment-code']")
    private WebElement accessCode;

    public String saveCreatedCourseAccessCode() {
        return action.getText(accessCode);
    }


}
