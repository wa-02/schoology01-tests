package org.example.schoology.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.core.ScenarioContext;
import org.example.core.ui.DriverFactory;
import org.example.schoology.pages.Home;
import org.example.schoology.pages.resources.DeleteRubricPopup;
import org.example.schoology.pages.resources.Resources;

public class RubricHooks {

    private final ScenarioContext context;

    public RubricHooks(final ScenarioContext context) {
        this.context = context;
    }

    @Before
    public void beforeScenario() {
        // this will be executed in all the scenarios

    }


    @After(value = "@deleteRubric")
    public void deleteRubric() {
        DriverFactory.getDriver().get("https://app.schoology.com");
        Resources resources = new Home().clickResourcesMenu();
        String rubricName = context.getValue("RubricKey");
        DeleteRubricPopup deleteRubricPopup = resources.clickDeleteRubric(rubricName);
        deleteRubricPopup.clickDeleteButton();
    }

}
