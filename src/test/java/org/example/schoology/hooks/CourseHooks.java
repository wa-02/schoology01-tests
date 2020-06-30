package org.example.schoology.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.core.Internationalization;
import org.example.core.ScenarioContext;
import org.example.core.ui.DriverFactory;
import org.example.schoology.pages.courses.Courses;
import org.example.schoology.pages.courses.DeleteCoursePopup;
import org.example.schoology.pages.Home;
import org.example.schoology.pages.SubMenu;

public class CourseHooks {

    private ScenarioContext context;

    public CourseHooks(final ScenarioContext context) {
        this.context = context;
    }

    @Before
    public void beforeScenario() {
        // this will be executed in all the scenarios

    }


    @After(value = "@deleteCourse")
    public void deleteCourse() {
        // What is the course name for deleting ?

        // delete by UI (~10 sec)
        DriverFactory.getDriver().get("https://app.schoology.com");
        String menu = Internationalization.getInstance().getValue("menu");
        SubMenu subMenu = new Home().clickMenu(menu);
        subMenu.clickViewListLink(menu);
        DeleteCoursePopup deleteCoursePopup = new Courses().clickDeleteCourse(context.getValue("CourseKey"));
        deleteCoursePopup.clickDeleteButton();

        // delete by Rest API (~3 milli seconds)
    }

}
