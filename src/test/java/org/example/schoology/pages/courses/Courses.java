package org.example.schoology.pages.courses;

import org.example.schoology.pages.DeletePopup;
import org.example.schoology.pages.ViewList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Courses extends ViewList {

    public static final String XPATH_COURSE_ACTIONS_BUTTON =
            "//span[text()='%s']/ancestor::li//div[@class='action-links-unfold ']";
    public static final String XPATH_SECTION_BY_NAME =
            "//span[text()='%s']/parent::p/parent::li//a[@class='sExtlink-processed']";

    @FindBy(css = "a.create-course-btn")
    private WebElement createCourseButton;

    @FindBy(css = "ul[style=\"display: block;\"] .action-edit")
    private WebElement editCourse;

    @FindBy(css = "ul[style=\"display: block;\"] .action-delete-link")
    private WebElement deleteCourse;

    public CreateCoursePopup clickCreateCourseButton() {
        action.click(createCourseButton);
        return new CreateCoursePopup();
    }

    public EditCoursePopup clickEditCourse(final String courseName) {
        WebElement courseActionsButton = driver.findElement(By.xpath(String.format(XPATH_COURSE_ACTIONS_BUTTON,
                courseName)));

        // Scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", courseActionsButton);

        action.click(courseActionsButton);
        action.click(editCourse);
        return new EditCoursePopup();
    }

    public DeletePopup clickDeleteCourse(final String courseName) {
        WebElement courseActionsButton = driver.findElement(By.xpath(String.format(XPATH_COURSE_ACTIONS_BUTTON,
                courseName)));

        // Scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", courseActionsButton);

        action.click(courseActionsButton);
        action.click(deleteCourse);

        return new DeletePopup();
    }

    public String getSectionByName(final String courseName) {
        return driver.findElement(By.xpath(String.format(XPATH_SECTION_BY_NAME, courseName))).getText();
    }
}
