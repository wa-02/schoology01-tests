package org.example.schoology.pages.resources;

import org.example.schoology.pages.ViewList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Resources extends ViewList {

    @FindBy(css = "a[href=\"/resources/my/add\"]")
    private WebElement addCollectionButton;

    public static final String COLLECTION_BY_NAME = "//a[text()='%s']";

    @FindBy(css = "#collection-share-link")
    private WebElement shareButton;

    private final By deletePopUp = By.cssSelector("#popups-1");

    @FindBy(xpath = "//input[@value='Delete']")
    private WebElement deleteButton;

    private static final String XPATH_RESOURCE_BY_NAME = ("//a[text()='%s']");

    public static final String RESOURCE_ACTION_BUTTON =
            "//a[text()='%s']/parent::td/following-sibling::td/child::div/child::div[@role='button']";
    public static final String DELETE_RESOURCE_ACTION_BUTTON =
            "//a[text()='%s']/ancestor::tr//ul//li[contains(@class,'action-delete')]";

     public AddCollectionPopup clickAddCollection() {
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
         return driver.findElement(By.xpath(String.format(COLLECTION_BY_NAME, collectionName))).getText();
    }
}
