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
import org.example.schoology.pages.courses.Courses;
import org.example.schoology.pages.SubMenu;
import org.example.schoology.pages.SubMenuTemplate;
import org.example.schoology.pages.courses.ImportFromResourcesPopup;
import org.example.schoology.pages.courses.ImportResourcePopup;
import org.example.schoology.pages.courses.Course;
import org.example.schoology.pages.courses.Courses;
import org.example.schoology.pages.courses.JoinCoursePopup;
import org.example.schoology.pages.resources.AddCollectionPopup;
import org.example.schoology.pages.resources.AddTemplatePopup;
import org.example.schoology.pages.resources.Resources;
import org.example.schoology.pages.resources.AddCollectionPopup;
import org.example.schoology.pages.resources.AddRubricPopup;
import org.example.schoology.pages.resources.AddToCoursePopup;
import org.example.schoology.pages.resources.ShareSettingsPopup;
import org.junit.Assert;

import java.util.Map;

public class ResourceStepDefs {


    private final ScenarioContext context;
    private Resources resources;
    private Home home;
    private Courses course;
    private SubMenu subMenu;

    private SubMenu subMenu;

    private Course course;

    private AddCollectionPopup addCollectionPopup;
    private ShareSettingsPopup shareSettingPopup;
    private AddRubricPopup addRubricPopup;
    private AddToCoursePopup addCourse;

    private AddTemplatePopup addTemplatePopup;

    private SubMenuTemplate subMenuTemplate;

    private ImportResourcePopup importResourcePopup;

    private ImportFromResourcesPopup importFromResourcePopup;

    public ResourceStepDefs(final SharedDriver sharedDriver, final ScenarioContext context, final Home home) {
        this.home = home;
        this.context = context;
    }

    private void loginAs(final String account) {
        Login login = new Login();
        home = login.loginAs(Environment.getInstance().getValue(String.format("credentials.%s.username", account)),
                Environment.getInstance().getValue(String.format("credentials.%s.password", account)));
    }

    @And("I create a resource collection with:")
    public void iCreateAResourceCollectionWith(final Map<String, String> datatable) {
        resources = home.clickResourcesMenu();
        addCollectionPopup = resources.clickAddCollection();
        resources = addCollectionPopup.create(datatable);
        context.setContext("CollectionTitle", datatable.get("title"));
    }


    @Given("I login as {string} user")
    public void iLogInAsUser(final String account) {
        Login login = new Login();
        home = login.loginAs(Environment.getInstance().getValue(String.format("credentials.%s.username", account)),
                Environment.getInstance().getValue(String.format("credentials.%s.password", account)));
        context.setContext("userName", Environment.getInstance()
                .getValue(String.format("credentials.%s.username", account)));
        context.setContext("passWord", Environment.getInstance()
                .getValue(String.format("credentials.%s.password", account)));
    }

    @When("I share the {string} collection with {string}")
    public void iShareTheCollectionWith(final String collection, final String instructorTwo) {
        shareSettingPopup = resources.clickShareCollection(collection);
        shareSettingPopup.searchTeacher(instructorTwo);
    }

    @When("I navigate to Resources")
    public void iNavigateToTheResources() {
        resources = home.clickResourcesMenu();
    }

    @Then("I should see the {string} title collection")
    public void iShouldSeeTheTitleCollection(final String collectionTitle) {
    @Then("I should see the {string} title collection of the {string}")
    public void iShouldSeeTheTitleCollection(final String collectionTitle, final String instructorTwo) {
        Assert.assertEquals(collectionTitle, resources.getCollectionByName(collectionTitle));
        loginAs(instructorTwo);
    }

    @And("I create a Quiz resource with:")
    public void iCreateAResourceWith(final Map<String, String> datatable) {
        resources = new Home().clickResourcesMenu();
        addTemplatePopup = resources.clickAddTestQuiz();
        subMenuTemplate = addTemplatePopup.create(datatable);
        context.setContext("ResourceNameKey", datatable.get("name"));
    }

    @When("I add the {string} to the course created")
    public void iAddTheToThe(final String testQuiz) {
         String menu = Internationalization.getInstance().getValue("menu");
         subMenu = subMenuTemplate.clickMenu(menu);
         subMenu.clickViewListLink(menu);
         course = subMenu.clickCourseSection(context.getValue("SectionKey"));
         importResourcePopup = course.clickAddMaterials();
         importFromResourcePopup = importResourcePopup.addResource(testQuiz);
         course = importFromResourcePopup.importCourse();
    }

    @And("I join to the course created")
    public void iJoinToTheCourseCreated() {
        String menu = Internationalization.getInstance().getValue("menu");
        subMenu = home.clickMenu(menu);
        subMenu.clickViewListLink(menu);
        JoinCoursePopup joinCoursePopup = new Courses().clickJoinCourseButton();
        joinCoursePopup.join(context.getValue("accessCode"));
    }



    @Then("I should see the {string} resource of my {string} in my course list")
    public void iShouldSeeTheResourceSharedWithTheCourse(final String resource, final String instructor) {
        Assert.assertTrue(resources.getResourceByName(resource));
        loginAs(instructor);
    }

    @And("I create a rubric with:")
    public void iCreateARubricWith(final Map<String, String> datatable) {
        resources = home.clickResourcesMenu();
        addRubricPopup = resources.clickAddRubric();
        resources = addRubricPopup.create(datatable);
        context.setContext("RubricKey", datatable.get("title"));
    }

    @And("I add the rubric to Course {string}")
    public void iAddTheRubricToCourse(final String courseName) {
        addCourse = resources.clickAddRubricToCourse(courseName);
        addCourse.selectCourse(courseName);
    }

    @Then("I should see the rubric {string} in list")
    public void iShouldSeeTheRubricInCourse(final String rubricName) {
        Assert.assertEquals(rubricName, resources.getRubricByName(rubricName));
    }
}
