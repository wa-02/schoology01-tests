package org.example.schoology.pages.courses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddResourceToCoursePopup extends AbstractCoursePopup {

    public static final String COURSE_NAME = "//span[text()='%s']";

    @FindBy (xpath = "//div[@class=\"addl-grading-category add-tooltip\"]")
    private WebElement selectCategoryCourse;

    public void addCourse (String testQuiz, String course) {
        action.click(By.xpath(String.format(COURSE_NAME, course)));
        action.click(selectCategoryCourse);


    }

}
