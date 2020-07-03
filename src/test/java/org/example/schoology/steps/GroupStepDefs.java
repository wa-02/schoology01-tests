package org.example.schoology.steps;

import java.util.Map;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.example.core.ScenarioContext;
import org.example.core.ui.SharedDriver;
import org.example.schoology.pages.Home;
import org.example.schoology.pages.SubMenu;
import org.example.schoology.pages.groups.Groups;
import org.example.schoology.pages.groups.DetailGroup;
import org.example.schoology.pages.groups.EditGroupPopup;
import org.example.schoology.pages.groups.CreateGroupPopup;
import org.example.schoology.pages.groups.Group;
import org.example.schoology.pages.groups.JoinGroupPopup;
import org.testng.Assert;

public class GroupStepDefs {

    private ScenarioContext context;

    private final Groups groups;

    public GroupStepDefs(final SharedDriver sharedDriver, final ScenarioContext context, final Groups groups) {
        this.context = context;
        this.groups = groups;
    }

    @And("I create a group with:")
    public void iCreateAGroupWith(final Map<String, String> datatable) {
        String menu = "Groups";
        SubMenu subMenu = new Home().clickMenu(menu);
        subMenu.clickViewListLink(menu);
        CreateGroupPopup createGroupPopup = this.groups.clickCreateGroupButton();
        Group group = createGroupPopup.create(datatable);
        context.setContext("GroupKey", datatable.get("name"));
    }

    @And("I edit the {string} group with:")
    public void iEditTheGroupWith(final String name, final Map<String, String> datatable) {
        EditGroupPopup editGroupPopup = groups.clickEditGroup(name);
        editGroupPopup.edit(datatable);
        context.setContext("GroupKey", datatable.get("name"));
    }

    @And("I should see a group with {string} as a name")
    public void iShouldSeeAGroupWithAsName(final String groupName) {
        Assert.assertEquals(groupName, groups.getGroupByName(groupName));
    }

    @And("I join in a group with Access code")
    public void iJoinInAGroupWithAccessCode() {
        JoinGroupPopup joinGroupPopup = this.groups.clickJoinGroupButton();
        joinGroupPopup.join(context.getValue("ValidAccessCodeKey"));
    }

    @And("I should not see a group with {string} as a name")
    public void iShouldNotSeeAGroupWithAsName(final String groupName) {
        Assert.assertEquals(false, groups.existGroupByName(groupName));
    }

    @And("I access to {string} Group detail")
    public void iAccessToGroupDetail(final String groupName) {
        groups.clickDetailGroupByName(groupName);
    }

    @And("I add a post {string}")
    public void iAddAPost(final String descriptionPost) {
        new DetailGroup().saveDescriptionPost(descriptionPost);
    }

    @Then("I should see a post with {string} as description")
    public void iShouldSeeAPostWithAsDescription(final String descriptionPost) {
        Assert.assertEquals(descriptionPost, new DetailGroup().getPostByDescription(descriptionPost));
    }

}
