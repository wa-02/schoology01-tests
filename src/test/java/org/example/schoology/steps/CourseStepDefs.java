package org.example.schoology.steps;

import java.util.Map;

import io.cucumber.java.en.And;
import org.example.core.Internationalization;
import org.example.core.ScenarioContext;
import org.example.core.ui.SharedDriver;
import org.example.schoology.pages.courses.Courses;
import org.example.schoology.pages.courses.CreateCoursePopup;
import org.example.schoology.pages.courses.EditCoursePopup;
import org.example.schoology.pages.groups.Groups;
import org.example.schoology.pages.Home;
import org.example.schoology.pages.SubMenu;
import org.testng.Assert;

public class CourseStepDefs {

    private ScenarioContext context;

    private SubMenu subMenu;

    private Courses courses;

    private Groups groups;

    private Home home;

    public CourseStepDefs(final SharedDriver sharedDriver, final ScenarioContext context,
                          final Courses courses) {
        this.context = context;
        this.home = new Home();
        this.courses = courses;
    }

    @And("I create a course with:")
    public void iCreateACourseWith(final Map<String, String> datatable) {
        String menu = Internationalization.getInstance().getValue("menu");
        subMenu = home.clickMenu(menu);
        subMenu.clickViewListLink(menu);
        CreateCoursePopup createCoursePopup = this.courses.clickCreateCourseButton();
        createCoursePopup.create(datatable);
        context.setContext("CourseKey", datatable.get("name"));
    }

    @And("I edit the {string} course with:")
    public void iEditTheCourseWith(final String name, final Map<String, String> datatable) {
        EditCoursePopup editCoursePopup = courses.clickEditCourse(name);
        courses = editCoursePopup.edit(datatable);
    }

    @And("I should see the {string} section on {string} course item")
    public void iShouldSeeTheSectionOnCourseItem(final String expectedSection, final String courseName) {
        Assert.assertEquals(expectedSection, courses.getSectionByName(courseName));
    }

}
