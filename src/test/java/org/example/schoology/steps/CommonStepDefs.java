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
import org.example.schoology.pages.SubMenu;
import org.example.schoology.pages.Login;
import org.example.schoology.pages.ViewList;
import org.example.schoology.pages.InfoPage;
import org.example.schoology.pages.courses.Badges;
import org.example.schoology.pages.courses.Course;
import org.testng.Assert;

public class CommonStepDefs {

    private Home home;

    private SubMenu subMenu;

    private ScenarioContext context;

    private Course course;

    private Badges badges;

    public CommonStepDefs(final SharedDriver sharedDriver, final ScenarioContext context) {

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

    @And("I create a badged of {string}")
    public void iCreateABadgedOf(final String badgedType) {
        String menu = Internationalization.getInstance().getValue("menu");
        subMenu = home.clickMenu(menu);
        subMenu.clickViewListLink(menu);
        course = subMenu.clickCourseSection(context.getValue("SectionKey"));
        badges = course.clickBadgeSectionTab();
        badges.clickAddBadged(badgedType);
    }

    @And("I add the badged to {string}")
    public void iAddTheBadgedTo(final String studentName) {
        badges.clickAddBadgeToStudent(
                Environment.getInstance().getValue(String.format("credentials.%s.firstName", studentName)),
                Environment.getInstance().getValue(String.format("credentials.%s.lastName", studentName)));
    }

    @And("I go to my badged section in my profile")
    public void iGoToMyBadgedSectionInMyProfile() {
        InfoPage infoPage = home.selectYourProfileAccount();
        infoPage.clickBadgesEstudentTab();
    }

    private void loginAs(final String account) {
        Login login = new Login();
        home = login.loginAs(Environment.getInstance().getValue(String.format("credentials.%s.username", account)),
                Environment.getInstance().getValue(String.format("credentials.%s.password", account)));
    }

    @Then("I should see {string} badged from my {string}")
    public void iShouldSeeBadged(final String badgedName, final String instructor) {
        Assert.assertEquals(badgedName, badges.getBadgedName(badgedName));
        loginAs(instructor);
    }
}
