package org.example.schoology.pages.courses;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class Course extends AbstractPage {

    private final By cssCourseProfile = By.cssSelector("#course-profile-materials");

    public Course() {
       wait.until(ExpectedConditions.visibilityOfElementLocated(cssCourseProfile));
    }
}
