package org.example.schoology.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.core.Environment;
import org.example.core.Internationalization;
import org.example.core.ScenarioContext;
import org.example.schoology.pages.Home;
import org.example.schoology.pages.Login;
import org.example.schoology.pages.SubMenu;
import org.example.schoology.pages.groups.Groups;
import org.example.schoology.pages.groups.DeleteGroupPopup;


public class GroupHooks {

    private ScenarioContext context;

    public GroupHooks(final ScenarioContext context) {
        this.context = context;
    }

    @Before
    public void beforeScenario() {
        // this will be executed in all the scenarios

    }


    @After(value = "@deleteGroupInstructor1")
    public void deleteCourse() {
        // What is the course name for deleting ?

        // delete by UI (~10 sec)
        String account = "Instructor01";
        Login login = new Login();

        login.loginAs(Environment.getInstance().getValue(String.format("credentials.%s.username", account)),
                Environment.getInstance().getValue(String.format("credentials.%s.password", account)));

        //DriverFactory.getDriver().get("https://app.schoology.com");
        String menu = Internationalization.getInstance().getValue("menu_group");
        SubMenu subMenu = new Home().clickMenu(menu);
        subMenu.clickViewListLink(menu);
        DeleteGroupPopup deleteGroupPopup = new Groups().clickDeleteGroup(context.getValue("GroupKey"));
        deleteGroupPopup.clickDeleteButton();

        // delete by Rest API (~3 milli seconds)
    }

}
