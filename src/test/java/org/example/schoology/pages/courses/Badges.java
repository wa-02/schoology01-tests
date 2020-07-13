package org.example.schoology.pages.courses;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Badges extends AbstractPage {

    public static final String BADGED_BY_TYPE =
            "//p[text()=\"%s\"]//parent::div//parent::td//preceding-sibling::td//child::input[@type=\"checkbox\"]";

    public static final String ADD_BADGED_TO_STUDENT =
            "//a[text()=\"%s, %s\"]//ancestor::div//following-sibling"
                    + "::div//descendant::td[@class=\"s-js-student-badge-cell badge-course-table-cell "
                    + "badge-course-main-table-cell student-badge-cell clickable sBadge-processed\"]";

    public static final String BADGED_BY_NAME = "//a[text()=\"%s\"]";

    @FindBy(css = "input[id=\"edit-submit\"]")
    private WebElement addButton;

    @FindBy(css = "//div[@class=\"message-text\"]")
    private WebElement statusBadgedDescription;

    public void clickAddBadged(final String badgedType) {
        action.click(By.xpath(String.format(BADGED_BY_TYPE, badgedType)));
        action.click(addButton);
    }

    public void clickAddBadgeToStudent(final String firstName, final String lastName) {
        action.click(By.xpath(String.format(ADD_BADGED_TO_STUDENT, lastName, firstName)));
    }

    public String getBadgedName(final String badgedName) {
        return driver.findElement(By.xpath(String.format(BADGED_BY_NAME, badgedName))).getText();
    }

}

