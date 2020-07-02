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
import org.example.schoology.pages.ViewList;
import org.example.schoology.pages.courses.DetailCourse;
import org.testng.Assert;

public class CommonStepDefs {

    private ScenarioContext context;

    private Home home;

    private SubMenu subMenu;

    public CommonStepDefs(final SharedDriver sharedDriver,final ScenarioContext context) {
        this.context = context;
    }

    @Given("I log in as {string} user")
    public void iLogInAsUser(final String account) {
        Login login = new Login();
        home = login.loginAs(Environment.getInstance().getValue(String.format("credentials.%s.username", account)),
                Environment.getInstance().getValue(String.format("credentials.%s.password", account)));
    }

    @When("I navigate to {string}")
    public void iNavigateToCourses(final String menu) {
        subMenu = home.clickMenu(menu);
        subMenu.clickViewListLink(menu);
    }

    @Then("I should see the {string} message")
    public void iShouldSeeTheMessage(final String message) {
        Assert.assertEquals(message, new ViewList().getMessage());
    }

    @And("I save its Access Code")
    public void iSaveAnAccessCodeOfCurseOrGroup() {
        String accessCode = new DetailCourse().saveCreatedCourseAccessCode();
        context.setContext("ValidAccessCodeKey", accessCode);
    }

}
