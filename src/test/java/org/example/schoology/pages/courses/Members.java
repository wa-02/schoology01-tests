package org.example.schoology.pages.courses;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Members extends AbstractPage {

    public static final String CSS_MEMBER_NAME = "tr.enrollment-member>td.user-name>a";

    @FindBy(css = ".members-filter")
    private WebElement membersTab;

    @FindBy(css = "tr.enrollment-member>td.user-name>a")
    private WebElement fullNameText;

    public void clickMembers() {
        action.click(membersTab);
    }

    public boolean isMember(final String firstName, final String lastName) {
        boolean boolMember = false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CSS_MEMBER_NAME)));
        List<WebElement> memberList = driver.findElements(By.cssSelector(CSS_MEMBER_NAME));
        for (WebElement member : memberList) {
            if (member.getText().equals(firstName + " " + lastName)) {
                boolMember = true;
            }
        }
        return boolMember;
    }
}
