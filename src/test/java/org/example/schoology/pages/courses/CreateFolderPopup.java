package org.example.schoology.pages.courses;

import org.example.core.ui.AbstractPage;
import org.example.schoology.pages.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CreateFolderPopup extends AbstractPage {
    private final By boldButton = By.cssSelector("#edit-description_bold");


    public static final String XPATH_FOLDER_COLOR =
            "//li[@class='s-js-color-selector-list-item']//div[@data-color='%s']";

    @FindBy(css = "#edit-title")
    private WebElement titleTextField;

    @FindBy(css = "#tinymce")
    private WebElement descriptionTextField;

    @FindBy(css = ".hasDatepicker")
    private WebElement dateTextField;

    @FindBy(css = "#edit-availability-start-datepicker-popup-0-wrapper")
    private WebElement dateButton;

    @FindBy(css = ".form-select")
    private WebElement AvailabilityDropDown;

    @FindBy(css = "#edit-submit")
    protected WebElement submitButton;

    public void fill(final Map<String, String> folderMap) {
        Map<String, Step> stepsMap = new HashMap<>();
        stepsMap.put("title", () -> setTitle(folderMap.get("title")));
        stepsMap.put("color", () -> setColor(folderMap.get("color")));
        stepsMap.put("description", () -> setDescription(folderMap.get("description")));
        stepsMap.put("date", () -> setDate(folderMap.get("date")));
        stepsMap.put("availability", () -> selectAvailability(folderMap.get("availability")));

        for (final String keyField : folderMap.keySet()) {
            stepsMap.get(keyField).execute();
        }
    }

    public void setTitle(final String title) {
        titleTextField.sendKeys(title);
    }

    public void setColor(final String color) {
        WebElement folderColor = driver.findElement(By.xpath(String.format(XPATH_FOLDER_COLOR, color)));
        action.click(folderColor);
    }

    private void setDescription(final String description) {
        driver.switchTo().frame("edit-description_ifr");
        descriptionTextField.sendKeys(description);
        driver.switchTo().defaultContent();
    }

    public void setDate(final String date) {
        dateButton.click();
        dateTextField.sendKeys(date);
    }

    public void selectAvailability(final String availability) {
        Select subjectArea = new Select(AvailabilityDropDown);
        subjectArea.selectByVisibleText(availability);
    }

    public Course createFolder(final Map<String, String> folderMap) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(boldButton));
        fill(folderMap);
        submitButton.submit();
        return new Course();
    }

}
