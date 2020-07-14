package org.example.schoology.pages.resources;

import org.example.schoology.pages.ViewList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Resources extends ViewList {

    public static final String GROUP_ACTIONS_BUTTON =
            "//div/span[text()='%s']/ancestor::tr//div[@href='#']";
    public static final String ADD_TO_COURSE =
            "//span[text()='%s']/ancestor::tr//a[@class='action-add-course sExtlink-processed popups-processed']";
    public static final String DELETE_RUBRIC_ACTION =
            "//span[text()='%s']/ancestor::tr//a[@class='action-delete  sExtlink-processed popups-processed']";
    public static final String COLLECTION_BY_NAME = "//a[text()= '%s']";
    public static final String RUBRIC_BY_NAME = "//div/span[text()= '%s']";

    @FindBy(css = "a[id=\"add-collection-btn\"]")
    private WebElement addCollectionButton;

    private static final String XPATH_RESOURCE_BY_NAME = "//a[text()='%s']";

    public static final String RESOURCE_BY_NAME =
            "//a[text()='%s']/parent::td/following-sibling::td//child::div[@role='button']";

    private final By deletePopUp = By.xpath("//div[text()=\"Delete\"]");

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

    @FindBy(xpath = "//img[@class='action-links-unfold-icon']//ancestor::div[@id='toolbar-add-wrapper']")
    private WebElement addResourceButton;

    @FindBy(xpath = "//li//child::a[text()=\"Add Test/Quiz\"]")
    private WebElement addTestQuizButton;

    @FindBy(xpath = "//a[@class=\"action-add-course sExtlink-processed popups-processed\"]")
    private WebElement addResourceToCourseButton;

    @FindBy(xpath = "//a[text()='Join a Course']")
    private WebElement joinACourseButton;

    @FindBy(xpath = "//input[@value='Delete']")
    private WebElement deleteButton;

    @FindBy(xpath = "//h2[text()=\"Home\"]")
    private WebElement resourceActionButton;

    public static final String RESOURCE_ACTION_BUTTON =
            "//a[text()='%s']/parent::td/following-sibling::td/child::div/child::div[@role='button']";
    public static final String DELETE_RESOURCE_ACTION_BUTTON =
            "//a[text()='%s']/ancestor::tr//ul//li[contains(@class,'action-delete')]";

    public AddTemplatePopup clickAddTestQuiz() {
        addResourceButton.click();
        addTestQuizButton.click();
        return new AddTemplatePopup();
    }

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
        return action.getText(By.xpath(String.format(COLLECTION_BY_NAME, collectionName)));

    }

    public DeleteResourceCollectionPopup clickDeleteCollection(final String collectionTitle) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"icon home\"]")));
        action.click(By.xpath(String.format(COLLECTION_BY_NAME, collectionTitle)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title=\"View user profile.\"]")));
        action.click(optionsCollectionButton);
        action.click(deleteCollectionButton);
        return new DeleteResourceCollectionPopup();
    }


    public void clickDeleteResource(final String resourceName) {
        wait.until(ExpectedConditions.visibilityOf(resourceActionButton));
        action.click(By.xpath(String.format(RESOURCE_ACTION_BUTTON, resourceName)));
        action.click(By.xpath(String.format(DELETE_RESOURCE_ACTION_BUTTON, resourceName)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(deletePopUp));
        action.click(deleteButton);
    }

    public boolean getResourceByName(final String resourceName) {
        try {
            return action.getStatus(By.xpath(String.format(XPATH_RESOURCE_BY_NAME, resourceName)));
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public AddRubricPopup clickAddRubric() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='toolbar-add']")));
        action.click(addResourcesComboBox);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Add Rubric']")));
        action.click(addRubricOption);
        return new AddRubricPopup();
    }

    public AddToCoursePopup clickAddRubricToCourse(final String courseName) {
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

    public DeleteRubricPopup clickDeleteRubric(final String rubricName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format(GROUP_ACTIONS_BUTTON, rubricName))));
        WebElement actionsButton = driver.findElement(By.xpath(String.format(GROUP_ACTIONS_BUTTON, rubricName)));
        action.click(actionsButton);

        WebElement deleteAction = driver.findElement(By.xpath(String.format(DELETE_RUBRIC_ACTION, rubricName)));
        action.click(deleteAction);
        return new DeleteRubricPopup();
    }
}
