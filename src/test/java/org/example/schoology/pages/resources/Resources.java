package org.example.schoology.pages.resources;

import org.example.schoology.pages.ViewList;
import org.example.schoology.pages.courses.AddResourceToCoursePopup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Resources extends ViewList {

    @FindBy(css = "a[id=\"add-collection-btn\"]")
    private WebElement addCollectionButton;

    public static final String COLLECTION_BY_NAME = "//a[text()='%s']";

    @FindBy(css = "#collection-share-link")
    private WebElement shareButton;

    @FindBy(css = "div[id='toolbar-options']")
    private WebElement optionsCollectionButton;

    @FindBy(xpath = "//a[@class=\"collection-delete sExtlink-processed popups-processed\"]")
    private WebElement deleteCollectionButton;

    @FindBy (xpath = "//img[@class='action-links-unfold-icon']//ancestor::div[@id='toolbar-add-wrapper']")
    private WebElement addResourceButton;

    @FindBy (xpath = "//li//child::a[text()=\"Add Test/Quiz\"]")
    private WebElement addTestQuizButton;

    public static final String RESOURCE_BY_NAME = "//a[text()='%s']/parent::td/following-sibling::td//child::div[@role='button']";

    @FindBy (xpath = "//a[@class=\"action-add-course sExtlink-processed popups-processed\"]")
    private WebElement addResoruceToCourseButton;

    public AddTemplatePopup clickAddTestQuiz(){
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
        action.click(By.xpath(String.format(COLLECTION_BY_NAME, collectionName)));
        action.click(shareButton);
        return new ShareSettingsPopup();
    }

    public String getCollectionByName(final String collectionName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Shared by Others\"]")));
        return driver.findElement(By.xpath(String.format(COLLECTION_BY_NAME, collectionName))).getText();
    }

    public DeleteResourceCollectionPopup clickDeleteCollection(final String collectionTitle) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"icon home\"]")));
        action.click(By.xpath(String.format(COLLECTION_BY_NAME, collectionTitle)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title=\"View user profile.\"]")));
        action.click(optionsCollectionButton);
        action.click(deleteCollectionButton);
        return new DeleteResourceCollectionPopup();
    }

    public AddResourceToCoursePopup clickAddResourceToCourse(final String resourceName) {
        action.click(By.xpath(String.format(RESOURCE_BY_NAME, resourceName)));
        action.click(addResoruceToCourseButton);
        return new AddResourceToCoursePopup();
    }

}
