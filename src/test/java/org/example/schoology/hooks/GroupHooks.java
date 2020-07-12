package org.example.schoology.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.core.Environment;
import org.example.core.ScenarioContext;
import org.example.core.ui.DriverFactory;
import org.example.schoology.pages.Login;
import org.example.schoology.pages.DeletePopup;
import org.example.schoology.pages.groups.Groups;


public class GroupHooks {

    private ScenarioContext context;

    public GroupHooks(final ScenarioContext context) {
        this.context = context;
    }

    @Before
    public void beforeScenario() {
        // this will be executed in all the scenarios

    }



    @After(value = "@deleteGroupInstructor")
    public void deleteGroupFirstInstructor() {
        // What is the course name for deleting ?

        // delete by UI (~10 sec)
        //Delete a curse when a scenario works with more than 1 instructor
        //it is required to login again after completing an scenario
        String account = context.getValue("InstructorName");
        Login login = new Login();

        login.loginAs(Environment.getInstance().getValue(String.format("credentials.%s.username", account)),
                Environment.getInstance().getValue(String.format("credentials.%s.password", account)));

        deleteGroup();
    }

    @After(value = "@deleteGroup")
    public void deleteGroupAnyInstructor() {
        deleteGroup();

    }
    private void deleteGroup() {
        DriverFactory.getDriver().get("https://app.schoology.com/groups");
        DeletePopup deleteGroupPopup = new Groups().clickDeleteGroup(context.getValue("GroupKey"));
        deleteGroupPopup.clickDeleteButton();

    }


}
