package org.example.schoology.pages.resources;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddToCoursePopup extends AbstractPage {

    public static final String COURSE_BY_NAME = "//span[text()='%s']";

    @FindBy(css = "input[placeholder='Add Title']")
    private WebElement criteriaTextField;

    @FindBy(xpath = "//input[@value=\"Add\"]")
    private WebElement submitButton;


    public void selectCourse(final String courseName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format(COURSE_BY_NAME, courseName))));
        action.click(By.xpath(String.format(COURSE_BY_NAME, courseName)));
        action.click(submitButton);
    }
}
