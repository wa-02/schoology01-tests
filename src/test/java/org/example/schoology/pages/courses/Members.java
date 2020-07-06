package org.example.schoology.pages.courses;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class Members extends AbstractPage {
    @FindBy(css = ".members-filter")
    private WebElement membersTab;

    @FindBy(css = "tr.enrollment-member>td.user-name>a.sExtlink-processed")
    private WebElement fullNameText;

    public void clickMembers() {
        membersTab.click();
    }

    public void searchStudent(final String firstName, final String lastName) {
        Assert.assertEquals(fullNameText.getText(), firstName + " " + lastName);
    }
}
