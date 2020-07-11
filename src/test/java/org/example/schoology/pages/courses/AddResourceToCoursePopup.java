package org.example.schoology.pages.courses;

import io.cucumber.core.resource.Resource;
import org.example.schoology.pages.resources.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddResourceToCoursePopup extends AbstractCoursePopup {

    public static final String COURSE_NAME = "//span[@class=\"s-library-export-course-select-course-title\" and text()=\"%s\"]";

    @FindBy (xpath = "//div[@class=\"addl-grading-category add-tooltip\"]//child::select[@class=\"form-select\"]")
    private WebElement selectCategoryCourse;

    @FindBy(xpath = "//div[@class=\"addl-grading-category add-tooltip\"]//child::select[@class=\"form-select\"]/child::option[text()=\"(Ungraded)\"]")
    private WebElement selectUngraded;

    @FindBy(css = "//input[@id=\"edit-submit\" and @value=\"Add\"]")
    private WebElement addButton;


    public Resources addCourse (String course) {
        action.click(By.xpath(String.format(COURSE_NAME, course)));
        action.click(selectCategoryCourse);
        action.click(selectUngraded);
        action.click(addButton);
        return new Resources();
    }

}
