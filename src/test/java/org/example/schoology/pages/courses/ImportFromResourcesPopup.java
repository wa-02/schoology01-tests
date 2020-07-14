package org.example.schoology.pages.courses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ImportFromResourcesPopup extends AbstractCoursePopup {

    @FindBy(css = "input[id=\"edit-submit\"]")
    private WebElement importButton;

    public Course importCourse() {
        action.click(importButton);
        return new Course();
    }
}
