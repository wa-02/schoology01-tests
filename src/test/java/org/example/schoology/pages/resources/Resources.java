package org.example.schoology.pages.resources;

import org.example.schoology.pages.ViewList;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Resources extends ViewList {

    @FindBy(css = "a[id=\"add-collection-btn\"]")
    private WebElement addCollectionButton;

    public static final String COLLECTION_BY_NAME = "//a[text()='%s']";

    private static final String XPATH_RESOURCE_BY_NAME = "//a[text()='%s']";

    public static final String RESOURCE_BY_NAME =
            "//a[text()='%s']/parent::td/following-sibling::td//child::div[@role='button']";

    @FindBy(css = "#collection-share-link")
    private WebElement shareButton;

    @FindBy(css = "div[id='toolbar-options']")
    private WebElement optionsCollectionButton;

    @FindBy(xpath = "//a[@class=\"collection-delete sExtlink-processed popups-processed\"]")
    private WebElement deleteCollectionButton;

    @FindBy(xpath = "//img[@class='action-links-unfold-icon']//ancestor::div[@id='toolbar-add-wrapper']")
    private WebElement addResourceButton;

    @FindBy(xpath = "//li//child::a[text()=\"Add Test/Quiz\"]")
    private WebElement addTestQuizButton;

    @FindBy(xpath = "//a[@class=\"action-add-course sExtlink-processed popups-processed\"]")
    private WebElement addResourceToCourseButton;

    @FindBy(xpath = "//a[text()='Join a Course']")
    private WebElement joinACourseButton;

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

    public boolean getResourceByName(final String resourceName) {
        try {
            return action.getStatus(By.xpath(String.format(XPATH_RESOURCE_BY_NAME, resourceName)));
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
