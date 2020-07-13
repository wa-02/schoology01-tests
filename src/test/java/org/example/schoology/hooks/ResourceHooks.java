package org.example.schoology.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.core.ScenarioContext;
import org.example.core.ui.DriverFactory;
import org.example.schoology.pages.Login;
import org.example.schoology.pages.Home;
import org.example.schoology.pages.resources.DeleteResourceCollectionPopup;
import org.example.schoology.pages.resources.Resources;

public class ResourceHooks {

    private ScenarioContext context;

    public ResourceHooks(final ScenarioContext context) {
        this.context = context;
    }

    @Before
    public void beforeScenario() {
        // this will be executed in all the scenarios

    }

    @After(value = "@deleteResourceCollection")
    public void deleteResourceCollection() {

        DriverFactory.getDriver().get("https://app.schoology.com");
        Login login = new Login();
        Home home = login.loginAs(context.getValue("userName"), context.getValue("passWord"));

        Resources resources = home.clickResourcesMenu();
        DeleteResourceCollectionPopup deleteResourceCollectionPopup =
                resources.clickDeleteCollection(context.getValue("CollectionTitle"));
        deleteResourceCollectionPopup.clickDeleteButton();
    }

    @After(value = "@deleteResource")
    public void deleteResource() {
        DriverFactory.getDriver().get("https://app.schoology.com");
        Resources resources = new Home().clickResourcesMenu();
        resources.clickDeleteResource(context.getValue("ResourceNameKey"));
    }

}
