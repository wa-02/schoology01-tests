package org.example.schoology.pages.groups;

import org.example.schoology.pages.ViewList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Groups extends ViewList {

    public static final String GROUP_ACTIONS_BUTTON = "//a[text()='%s']/ancestor::li//div[@href='#']";
    public static final String SELECT_ACTIONS = "//a[text()='%s']/ancestor::li//ul//li[@class='action-edit']";
    public static final String GROUP_BY_NAME = "//a[text()='%s']";

    @FindBy(css = "a.create-group")
    private WebElement createGroupButton;

    public CreateGroupPopup clickCreateGroupButton() {
        action.click(createGroupButton);
        return new CreateGroupPopup();
    }

    public EditGroupPopup clickEditGroup(final String groupName) {
        WebElement groupActionsButton = driver.findElement(By.xpath(String.format(GROUP_ACTIONS_BUTTON, groupName)));
        // Scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", groupActionsButton);

        action.click(groupActionsButton);
        WebElement groupEditAction = driver.findElement(By.xpath(String.format(SELECT_ACTIONS, groupName)));
        action.click(groupEditAction);
        return new EditGroupPopup();
    }

    public String getGroupByName(final String groupName) {
        WebElement groupNameLabel =  driver.findElement(By.xpath(String.format(GROUP_BY_NAME, groupName)));
        return action.getText(groupNameLabel);
    }

}
