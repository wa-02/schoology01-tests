package org.example.schoology.pages.groups;

import org.example.schoology.pages.ViewList;
import org.example.schoology.pages.groups.DeleteGroupPopup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriverException;

public class Groups extends ViewList {

    public static final String GROUP_ACTIONS_BUTTON = "//a[text()='%s']/ancestor::li//div[@href='#']";
    public static final String SELECT_ACTIONS = "//a[text()='%s']/ancestor::li//ul//li[@class='action-edit']";
    public static final String GROUP_BY_NAME = "//a[text()='%s']";


    @FindBy(css = "a.create-group")
    private WebElement createGroupButton;

    @FindBy(css = "a.groups-enroll")
    private WebElement joinGroupButton;

    @FindBy(css = "ul[style='display: block;'] .action-delete")
    private WebElement deleteGroup;

    public CreateGroupPopup clickCreateGroupButton() {
        createGroupButton.click();
        return new CreateGroupPopup();
    }

    public JoinGroupPopup clickJoinGroupButton() {
        joinGroupButton.click();
        return new JoinGroupPopup();
    }


    public EditGroupPopup clickEditGroup(final String groupName) {
        WebElement groupActionsButton = driver.findElement(By.xpath(String.format(GROUP_ACTIONS_BUTTON, groupName)));
        // Scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", groupActionsButton);

        wait.until(ExpectedConditions.visibilityOf(groupActionsButton));
        groupActionsButton.click();
        driver.findElement(By.xpath(String.format(SELECT_ACTIONS, groupName))).click();
        return new EditGroupPopup();
    }

    public String getGroupByName(final String groupName) {
        return driver.findElement(By.xpath(String.format(GROUP_BY_NAME, groupName))).getText();
    }

    public void clickDetailGroupByName(final String groupName) {
        WebElement groupDetailLink = driver.findElement(By.xpath(String.format(GROUP_BY_NAME, groupName)));
        // Scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", groupDetailLink);

        wait.until(ExpectedConditions.visibilityOf(groupDetailLink));
        groupDetailLink.click();
    }

    public DeleteGroupPopup clickDeleteGroup(final String groupName) {
        WebElement groupActionsButton = driver.findElement(By.xpath(String.format(GROUP_ACTIONS_BUTTON,
                groupName)));

        // Scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", groupActionsButton);

        groupActionsButton.click();
        deleteGroup.click();
        return new DeleteGroupPopup();
    }

    public boolean existGroupByName(final String groupName) {
        try {
            WebElement groupItem = driver.findElement(By.xpath(String.format(GROUP_BY_NAME, groupName)));
            return true;
        } catch (WebDriverException e) {
            // nothing.
            return false;
        }finally {
            return false;
        }
    }

}
