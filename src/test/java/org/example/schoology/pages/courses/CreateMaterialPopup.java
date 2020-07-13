package org.example.schoology.pages.courses;

import org.example.core.ui.AbstractPage;
import org.example.schoology.pages.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.HashMap;
import java.util.Map;

public class CreateMaterialPopup extends AbstractPage {
    private final By boldButton = By.cssSelector("#edit-description_bold");

    public static final String XPATH_FOLDER_COLOR = "//div[@data-color='%s']";

    @FindBy(css = "#edit-title")
    private WebElement nameTextField;

    @FindBy(css = "#edit-title")
    private WebElement titleTextField;

    @FindBy(css = "#tinymce")
    private WebElement descriptionTextField;

    @FindBy(css = ".hasDatepicker")
    private WebElement dateTextField;

    @FindBy(css = "#edit-availability-start-datepicker-popup-0-wrapper")
    private WebElement dateButton;

    @FindBy(css = ".form-select")
    private WebElement availabilityDropDown;

    @FindBy(css = "#edit-grading-category-id")
    private WebElement categoryDropDown;

    @FindBy(css = "#edit-new-category")
    private WebElement categoryTextField;

    @FindBy(css = "#edit-link")
    private WebElement linkTextField;

    @FindBy(css = "#edit-link-title")
    private WebElement linkTitleTextField;


    @FindBy(css = "#edit-submit")
    protected WebElement submitButton;

    public void fill(final Map<String, String> folderMap) {
        Map<String, Step> stepsMap = new HashMap<>();
        stepsMap.put("name", () -> setName(folderMap.get("name")));
        stepsMap.put("title", () -> setTitle(folderMap.get("title")));
        stepsMap.put("color", () -> setColor(folderMap.get("color")));
        stepsMap.put("description", () -> setDescription(folderMap.get("description")));
        stepsMap.put("date", () -> setDate(folderMap.get("date")));
        stepsMap.put("availability", () -> selectAvailability(folderMap.get("availability")));
        stepsMap.put("category", () -> selectCategory(folderMap.get("category")));
        stepsMap.put("link", () -> setLink(folderMap.get("link")));
        stepsMap.put("linkTitle", () -> setLinkTitle(folderMap.get("linkTitle")));

        for (final String keyField : folderMap.keySet()) {
            stepsMap.get(keyField).execute();
        }
    }

    public void setName(final String title) {
        nameTextField.sendKeys(title);
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
        Select subjectArea = new Select(availabilityDropDown);
        subjectArea.selectByVisibleText(availability);
    }

    private void selectCategory(final String category) {
        Select subjectArea = new Select(categoryDropDown);
        subjectArea.selectByVisibleText("(Create new grading category)");
        categoryTextField.sendKeys(category);
    }

    public void setLink(final String title) {
        linkTextField.sendKeys(title);
    }

    public void setLinkTitle(final String title) {
        linkTitleTextField.sendKeys(title);
    }

    public Course createMaterial(final Map<String, String> folderMap) {
        for (String key : folderMap.keySet()) {
            if (key.equals("description")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(boldButton));
            }
        }

        fill(folderMap);
        submitButton.submit();
        return new Course();
    }

}
