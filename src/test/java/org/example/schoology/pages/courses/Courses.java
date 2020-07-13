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

    public static final String XPATH_COURSE_LINK =
            "//span[text()='%s']/ancestor::li//a[@class='sExtlink-processed']";

    public static final String XPATH_DELETE_BUTTON =
            "//span[text()='%s']/parent::p//a";

    @FindBy(css = "a.create-course-btn")
    private WebElement createCourseButton;

    @FindBy(xpath = "//a[text()=\"Join a Course\"]")
    private WebElement joinCourseButton;

    @FindBy(css = "ul[style=\"display: block;\"] .action-edit")
    private WebElement editCourse;

    @FindBy(css = "ul[style=\"display: block;\"] .action-delete-link")
    private WebElement deleteCourse;

    @FindBy(xpath = "//h3[text()=\"Manage Courses\"]")
    private WebElement manageCoursesDescription;

    public CreateCoursePopup clickCreateCourseButton() {
        action.click(createCourseButton);
        return new CreateCoursePopup();
    }

    public JoinCoursePopup clickJoinCourseButton() {
        //wait.until(ExpectedConditions.visibilityOf(manageCoursesDescription));
        action.click(joinCourseButton);
        return new JoinCoursePopup();
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

    public DeletePopup clickDeleteInactiveCourse(final String courseName) {
        WebElement courseDeleteButton = driver.findElement(By.xpath(String.format(XPATH_DELETE_BUTTON, courseName)));

        // Scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", courseDeleteButton);

        courseDeleteButton.click();
        return new DeletePopup();
    }

    public String getSectionByName(final String courseName) {
        WebElement sectionName =  driver.findElement(By.xpath(String.format(XPATH_SECTION_BY_NAME, courseName)));
        return action.getText(sectionName);
    }

    public Course clickCourseLink(final  String courseName) {
        WebElement courseLink =  driver.findElement(By.xpath(String.format(XPATH_COURSE_LINK, courseName)));
        action.click(courseLink);
        return new Course();
    }
}
