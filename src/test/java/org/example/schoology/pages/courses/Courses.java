package org.example.schoology.pages.courses;

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

    public static final String XPATH_COURSE_LINK =
            "//span[text()='%s']/ancestor::li//a[@class='sExtlink-processed']";

    @FindBy(css = "a.create-course-btn")
    private WebElement createCourseButton;

    @FindBy(css = "a.courses-enroll")
    private WebElement joinCourseButton;

    @FindBy(css = "ul[style=\"display: block;\"] .action-edit")
    private WebElement editCourse;

    @FindBy(css = "ul[style=\"display: block;\"] .action-delete-link")
    private WebElement deleteCourse;

    public CreateCoursePopup clickCreateCourseButton() {
        createCourseButton.click();
        return new CreateCoursePopup();
    }

    public JoinCoursePopup clickJoinCourseButton() {
        joinCourseButton.click();
        return new JoinCoursePopup();
    }


    public EditCoursePopup clickEditCourse(final String courseName) {
        WebElement courseActionsButton = driver.findElement(By.xpath(String.format(XPATH_COURSE_ACTIONS_BUTTON,
                courseName)));

        // Scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", courseActionsButton);

        courseActionsButton.click();
        editCourse.click();
        return new EditCoursePopup();
    }

    public DeleteCoursePopup clickDeleteCourse(final String courseName) {
        WebElement courseActionsButton = driver.findElement(By.xpath(String.format(XPATH_COURSE_ACTIONS_BUTTON,
                courseName)));

        // Scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", courseActionsButton);

        courseActionsButton.click();
        deleteCourse.click();
        return new DeleteCoursePopup();
    }

    public String getSectionByName(final String courseName) {
        return driver.findElement(By.xpath(String.format(XPATH_SECTION_BY_NAME, courseName))).getText();
    }

    public Course clickCourseLink(final  String courseName) {
        driver.findElement(By.xpath(String.format(XPATH_COURSE_LINK, courseName))).click();
        return new Course();
    }
}
