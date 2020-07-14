package org.example.schoology.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.core.Environment;
import org.example.core.Internationalization;
import org.example.core.ScenarioContext;
import org.example.core.ui.SharedDriver;
import org.example.schoology.pages.Home;
import org.example.schoology.pages.Login;
import org.example.schoology.pages.SubMenu;
import org.example.schoology.pages.courses.Course;
import org.example.schoology.pages.courses.Courses;
import org.example.schoology.pages.courses.CreateCoursePopup;
import org.example.schoology.pages.courses.CreateDiscussionPopup;
import org.example.schoology.pages.courses.CreateMaterialPopup;
import org.example.schoology.pages.courses.EditCoursePopup;
import org.example.schoology.pages.courses.JoinCoursePopup;
import org.example.schoology.pages.courses.Materials;
import org.example.schoology.pages.courses.Members;
import org.example.schoology.pages.courses.Updates;
import org.example.schoology.pages.groups.Groups;
import org.testng.Assert;

import java.util.Map;

public class CourseStepDefs {

    private final ScenarioContext context;

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

    private void loginAs(final String account) {
        Login login = new Login();
        home = login.loginAs(Environment.getInstance().getValue(String.format("credentials.%s.username", account)),
                Environment.getInstance().getValue(String.format("credentials.%s.password", account)));
    }

    private Course goToCourse(final String subject) {
        subMenu = home.clickMenu("Courses");
        subMenu.clickViewListLink("Courses");
        return courses.clickCourseLink(subject);
    }


    @And("I create a course with:")
    public void iCreateACourseWith(final Map<String, String> datatable) {
        String menu = Internationalization.getInstance().getValue("menu");
        subMenu = home.clickMenu(menu);
        subMenu.clickViewListLink(menu);
        CreateCoursePopup createCoursePopup = this.courses.clickCreateCourseButton();
        createCoursePopup.create(datatable);
        context.setContext("CourseKey", datatable.get("name"));
        context.setContext("SectionKey", datatable.get("section"));
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
        loginAs(account);

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
        loginAs(account);

        String menu = Internationalization.getInstance().getValue("menu");
        subMenu = home.clickMenu(menu);
        subMenu.clickViewListLink(menu);
        JoinCoursePopup joinCoursePopup = this.courses.clickJoinCourseButton();
        joinCoursePopup.join(context.getValue(code));
    }

    @Then("I am as {string} should have a {string} user in the {string} course")
    public void iAmAsShouldHaveAUserInTheCourse(final String account, final String member, final String subject) {
        // Login
        loginAs(account);

        Course course = goToCourse(subject);

        Members members = course.clickMembers();
        members.clickMembers();
        Assert.assertTrue(members.isMember(
                Environment.getInstance().getValue(String.format("credentials.%s.firstName", member)),
                Environment.getInstance().getValue(String.format("credentials.%s.lastName", member))));

    }

    @And("{string} is my student")
    public void isMyStudent(final String account) {
        iHaveTheCourseCode();
        useTheAccessCode(account, "AccessCode");
    }

    @When("I as {string} user of {string} course create a {string} for my class")
    public void iAsUserOfCourseCreateAForMyClass(final String account, final String subject, final String material,
                                                 final Map<String, String> datatable) {
        // Login
        loginAs(account);

        Course course = goToCourse(subject);

        Materials materials = course.clickMaterials();
        CreateMaterialPopup createMaterialPopup = materials.clickAddMaterials(material);
        createMaterialPopup.createMaterial(datatable);
    }


    @Then("{string} should have a {string} material in {string}'s {string} class.")
    public void shouldHaveAFolderInSClass(final String account1, final String materialName, final String account2,
                                          final String subject) {
        loginAs(account1);

        Course course = goToCourse(subject);

        Materials materials = course.clickMaterials();
        Assert.assertEquals(materials.getMaterial(), materialName);

        loginAs(account2);
    }

    @When("I add an update to {string} course as {string} with {string}")
    public void iAddAnUpdateToCourseAsWith(final String courseName, final String instructorName, final String update) {
        loginAs(instructorName);

        Course course = goToCourse(courseName);
        Updates updates = course.clickUpdates();
        updates.createUpdate(update);
        context.setContext("instructor", instructorName);
        context.setContext("description", update);
    }

    @Then("{string} should see the update in {string} class")
    public void shouldSeeTheUpdateInClass(final String student, final String courseName) {
        loginAs(student);

        Course course = goToCourse(courseName);

        Updates updates = course.clickUpdates();
        String description = context.getValue("description");
        Assert.assertEquals(updates.getUpdateText(description), description);

        String instructorName = context.getValue("instructor");
        loginAs(instructorName);
    }

    @When("I as {string} user of {string} course create a Discussion for my class with:")
    public void iAsUserOfCourseCreateADiscussionForMyClassWith(final String instructorName, final String courseName,
                                                               final Map<String, String> discussionData) {
        context.setContext("instructor", instructorName);
        context.setContext("course", courseName);
        // Login
        loginAs(instructorName);

        Course course = goToCourse(courseName);

        Materials materials = course.clickMaterials();
        CreateDiscussionPopup createDiscussionPopup = materials.clickAddDiscussion();
        createDiscussionPopup.createDiscussion(discussionData);
    }
}
