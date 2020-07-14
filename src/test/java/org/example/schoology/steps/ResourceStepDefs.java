package org.example.schoology.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.core.Environment;
import org.example.core.ScenarioContext;
import org.example.core.ui.SharedDriver;
import org.example.schoology.pages.Home;
import org.example.schoology.pages.Login;
import org.example.schoology.pages.SubMenu;
import org.example.schoology.pages.courses.Courses;
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
    private AddCollectionPopup addCollectionPopup;
    private ShareSettingsPopup shareSettingPopup;
    private AddRubricPopup addRubricPopup;
    private AddToCoursePopup addCourse;

    public ResourceStepDefs(final SharedDriver sharedDriver, final ScenarioContext context, final Home home) {
        this.home = home;
        this.context = context;
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
        Assert.assertEquals(collectionTitle, resources.getCollectionByName(collectionTitle));
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
