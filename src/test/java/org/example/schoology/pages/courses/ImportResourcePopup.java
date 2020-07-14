package org.example.schoology.pages.courses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ImportResourcePopup extends  AbstractCoursePopup {
    public static final String SELECT_COURSE_NAME =
            "//span[text()='%s']//parent::label//child::input[@type=\"checkbox\"]";

    @FindBy(css = "input[id=\"edit-submit-buttons-submit\"]")
    private WebElement importButton;

    public ImportFromResourcesPopup addResource(final String resourceName) {
        action.click(By.xpath(String.format(SELECT_COURSE_NAME, resourceName)));
        action.click(importButton);
        return new ImportFromResourcesPopup();
    }
}

