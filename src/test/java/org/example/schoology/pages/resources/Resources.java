package org.example.schoology.pages.resources;

import org.example.schoology.pages.DeletePopup;
import org.example.schoology.pages.ViewList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Resources extends ViewList {

    public static final String GROUP_ACTIONS_BUTTON = "//span[text()='%s']/ancestor::tr//div[@href='#']//span[@class='action-links-unfold-inline-image']";
    public static final String ADD_TO_COURSE = "//span[text()='%s']/ancestor::tr//a[@class='action-add-course sExtlink-processed popups-processed']";
    public static final String DELETE_RUBRIC_ACTION = "//span[text()='%s']/ancestor::tr//a[@class='action-delete  sExtlink-processed popups-processed']";
    public static final String COLLECTION_BY_NAME = "//a[text()= '%s']";
    public static final String RUBRIC_BY_NAME = "//div/span[text()= '%s']";

    @FindBy(css = "a[id=\"add-collection-btn\"]")
    private WebElement addCollectionButton;
    @FindBy(css = "#collection-share-link")
    private WebElement shareButton;

    @FindBy(css = "div[id='toolbar-options']")
    private WebElement optionsCollectionButton;

    @FindBy(xpath = "//a[@class=\"collection-delete sExtlink-processed popups-processed\"]")
    private WebElement deleteCollectionButton;

    @FindBy(xpath = "//*[@id='toolbar-add']")
    private WebElement addResourcesComboBox;

    @FindBy(xpath = "//span[text()='Add Rubric']")
    private WebElement addRubricOption;

    public AddCollectionPopup clickAddCollection() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"icon home\"]")));
        action.click(addCollectionButton);
        return new AddCollectionPopup();
    }

    public ShareSettingsPopup clickShareCollection(final String collectionName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"icon home\"]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format(COLLECTION_BY_NAME, collectionName))));
        action.click(By.xpath(String.format(COLLECTION_BY_NAME, collectionName)));
        action.click(shareButton);
        return new ShareSettingsPopup();
    }

    public String getCollectionByName(final String collectionName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Shared by Others\"]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format(COLLECTION_BY_NAME, collectionName))));
        return action.getText(driver.findElement(By.xpath(String.format(COLLECTION_BY_NAME, collectionName))));
    }

    public DeleteResourceCollectionPopup clickDeleteCollection(final String collectionTitle) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"icon home\"]")));
        action.click(By.xpath(String.format(COLLECTION_BY_NAME, collectionTitle)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title=\"View user profile.\"]")));
        action.click(optionsCollectionButton);
        action.click(deleteCollectionButton);
        return new DeleteResourceCollectionPopup();
    }

    public AddRubricPopup clickAddRubric() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='toolbar-add']")));
        action.click(addResourcesComboBox);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Add Rubric']")));
        action.click(addRubricOption);
        return new AddRubricPopup();
    }

    public AddToCoursePopup clickAddRubricToCourse(String courseName) {
        WebElement actionsButton = driver.findElement(By.xpath(String.format(GROUP_ACTIONS_BUTTON, courseName)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", actionsButton);

        action.click(actionsButton);
        WebElement addAction = driver.findElement(By.xpath(String.format(ADD_TO_COURSE, courseName)));
        action.click(addAction);
        return new AddToCoursePopup();
    }

    public String getRubricByName(final String rubricName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format(RUBRIC_BY_NAME, rubricName))));
        return action.getText(driver.findElement(By.xpath(String.format(RUBRIC_BY_NAME, rubricName))));
    }

    public DeleteRubricPopup clickDeleteRubric(String rubricName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(GROUP_ACTIONS_BUTTON, rubricName))));
        WebElement actionsButton = driver.findElement(By.xpath(String.format(GROUP_ACTIONS_BUTTON, rubricName)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", actionsButton);
        action.click(actionsButton);

        WebElement deleteAction = driver.findElement(By.xpath(String.format(DELETE_RUBRIC_ACTION, rubricName)));
        action.click(deleteAction);
        return new DeleteRubricPopup();
    }
}
