package org.example.schoology.pages.groups;

import org.example.schoology.pages.DeletePopup;
import org.example.schoology.pages.ViewList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class Groups extends ViewList {

    public static final String GROUP_ACTIONS_BUTTON = "//a[text()='%s']/ancestor::li//div[@href='#']";
    public static final String SELECT_ACTIONS = "//a[text()='%s']/ancestor::li//ul//li[@class='action-edit']";
    public static final String GROUP_BY_NAME = "//a[text()='%s']";

    @FindBy(css = "a.create-group")
    private WebElement createGroupButton;

    @FindBy(css = "ul[style='display: block;'] .action-delete")
    private WebElement deleteGroup;

    @FindBy(css = "a.groups-enroll")
    private WebElement joinGroupButton;


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

    public DeletePopup clickDeleteGroup(final String groupName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(GROUP_ACTIONS_BUTTON,
                groupName))));
        WebElement groupActionsButton = driver.findElement(By.xpath(String.format(GROUP_ACTIONS_BUTTON,
                groupName)));

        // Scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", groupActionsButton);

        action.click(groupActionsButton);
        action.click(deleteGroup);
        return new DeletePopup();
    }

    public JoinGroupPopup clickJoinGroupButton() {
        action.click(joinGroupButton);
        return new JoinGroupPopup();
    }


    public boolean existGroupByName(final String groupName) {
        boolean exist = false;
        if (!driver.findElements(By.xpath(String.format(GROUP_BY_NAME, groupName))).isEmpty()) {
            exist = true;
        }
        return exist;
    }


    public void clickDetailGroupByName(final String groupName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(GROUP_BY_NAME,
                groupName))));
        WebElement groupDetailLink = driver.findElement(By.xpath(String.format(GROUP_BY_NAME, groupName)));
        // Scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", groupDetailLink);

        wait.until(ExpectedConditions.visibilityOf(groupDetailLink));
        groupDetailLink.click();
    }


}
