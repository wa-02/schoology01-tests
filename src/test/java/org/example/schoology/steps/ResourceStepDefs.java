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
import org.example.schoology.pages.resources.AddCollectionPopup;
import org.example.schoology.pages.resources.Resources;
import org.example.schoology.pages.resources.ShareSettingsPopup;
import org.junit.Assert;

import java.util.Map;

public class ResourceStepDefs {


    private Resources resources;

    private Home home;

    private AddCollectionPopup addCollectionPopup;

    private ShareSettingsPopup shareSettingPopup;

    private ScenarioContext context;

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

}
