package org.example.schoology.pages.courses;

import org.example.schoology.pages.AbstractDetail;
import org.openqa.selenium.By;

public class Updates extends AbstractDetail {

    public static final String UPDATE_DESCRIPTION_XPATH = "//p[text()='%s']";

    public Updates createUpdate(final String descriptionUpdate) {
        driver.switchTo().frame("edit-body_ifr");
        driver.findElement(By.xpath("//body[contains(@class, 'mceContentBody')]")).click();
        driver.findElement(By.xpath("//body[contains(@class, 'mceContentBody')]/child::p")).sendKeys(descriptionUpdate);
        driver.switchTo().defaultContent();
        action.click(submitButton);

        return this;
    }

    public String getUpdateText(final String updateDescription) {
        return driver.findElement(By.xpath(String.format(UPDATE_DESCRIPTION_XPATH, updateDescription))).getText();
    }
}
