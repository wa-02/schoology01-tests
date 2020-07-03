package org.example.schoology.pages.groups;

import org.example.core.ui.AbstractPage;
import org.example.schoology.pages.AbstractDetail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DetailGroup extends AbstractDetail {

    public static final String XPATH_DESCRIPTION_POST_CREATED = "//p[text()='%s']";

    @FindBy(css = "body[class='mceContentBody ']")
    private WebElement postField;


    public void saveDescriptionPost(final String descriptionPost) {

        driver.switchTo().frame("edit-body_ifr");
        driver.findElement(By.xpath("//body[contains(@class, 'mceContentBody')]")).click();
        driver.findElement(By.xpath("//body[contains(@class, 'mceContentBody')]/child::p")).sendKeys(descriptionPost);
        driver.switchTo().defaultContent();
        action.click(submitButton);
    }

    public String getPostByDescription(final String descriptionPost) {
        return driver.findElement(By.xpath(String.format(XPATH_DESCRIPTION_POST_CREATED, descriptionPost))).getText();
    }

    public static class DeleteGroupPopup extends AbstractPage {

        @FindBy(css = "#edit-submit")
        private WebElement deleteButton;

        public void clickDeleteButton() {
            action.click(deleteButton);
        }

    }
}
