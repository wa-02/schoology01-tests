package org.example.schoology.pages.resources;

import org.example.core.Environment;
import org.example.core.ui.AbstractPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShareSettingsPopup extends AbstractPage {

    @FindBy(css = "#edit-people-search")
    private WebElement insertNameTeacher;

    @FindBy(xpath = "//span[@class=\"link-btn\"]")
    private WebElement searchTeacher;

    @FindBy(xpath = "//li[@class=\"user-invite-wrapper sEnrollmentInvite-processed\"]")
    private WebElement selectTeacher;

    @FindBy(xpath = "//input[@id=\"edit-submit-1\" and @value=\"Add People\"]")
    private WebElement addPeopleButton;

    public Resources searchTeacher(final String account) {
        insertNameTeacher.sendKeys(Environment.getInstance()
                .getValue(String.format("credentials.%s.firstName", account)));
        action.click(searchTeacher);
        action.click(selectTeacher);
        action.click(addPeopleButton);
        return new Resources();
    }
}
