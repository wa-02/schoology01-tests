package org.example.schoology.pages.courses;

import org.openqa.selenium.By;

public class AddResourceToCoursePopup extends AbstractCoursePopup {

    public static final String COURSE_NAME = "//span[text()='%s']";

    public void addResourceToCoursePop (String testQuiz, String course) {
        action.click(By.xpath(String.format(COURSE_NAME, course)));


    }

}
