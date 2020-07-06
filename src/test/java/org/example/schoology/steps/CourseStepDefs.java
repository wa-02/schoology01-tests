package org.example.schoology.steps;

import java.util.Map;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.core.Environment;
import org.example.core.Internationalization;
import org.example.core.ScenarioContext;
import org.example.core.ui.SharedDriver;
import org.example.schoology.pages.Login;
import org.example.schoology.pages.courses.Course;
import org.example.schoology.pages.courses.Courses;
import org.example.schoology.pages.courses.CreateCoursePopup;
import org.example.schoology.pages.courses.EditCoursePopup;
import org.example.schoology.pages.courses.JoinCoursePopup;
import org.example.schoology.pages.courses.Members;
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

    @Given("I am a {string} of:")
    public void iAmAOf(final String account, final Map<String, String> datatable) {
        // Login
        Login login = new Login();
        home = login.loginAs(Environment.getInstance().getValue(String.format("credentials.%s.username", account)),
                Environment.getInstance().getValue(String.format("credentials.%s.password", account)));

        // Create course
        iCreateACourseWith(datatable);
    }

    @And("I have the course code")
    public void iHaveTheCourseCode() {
        Course course = new Course();
        String code = course.getAccessCode();
        context.setContext("AccessCode", code);
    }

    @When("{string} user use the {string}")
    public void useTheAccessCode(final String account, final String code) {
        Login login = new Login();
        home = login.loginAs(Environment.getInstance().getValue(String.format("credentials.%s.username", account)),
                Environment.getInstance().getValue(String.format("credentials.%s.password", account)));

        String menu = Internationalization.getInstance().getValue("menu");
        subMenu = home.clickMenu(menu);
        subMenu.clickViewListLink(menu);
        JoinCoursePopup joinCoursePopup = this.courses.clickJoinCourseButton();
        joinCoursePopup.join(context.getValue(code));
    }

    @Then("I am as {string} should have a {string} user in the {string} course")
    public void iAmAsShouldHaveAUserInTheCourse(final String account, final String member, final String subject) {
        // Login
        Login login = new Login();
        home = login.loginAs(Environment.getInstance().getValue(String.format("credentials.%s.username", account)),
                Environment.getInstance().getValue(String.format("credentials.%s.password", account)));

        subMenu = home.clickMenu("Courses");
        subMenu.clickViewListLink("Courses");
        Course course = courses.clickCourseLink(subject);
        Members members = course.clickMembers();
        members.clickMembers();
        members.searchStudent(Environment.getInstance().getValue(String.format("credentials.%s.firstName", member)),
                Environment.getInstance().getValue(String.format("credentials.%s.lastName", member)));

    }
}
