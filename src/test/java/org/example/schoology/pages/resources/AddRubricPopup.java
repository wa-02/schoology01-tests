package org.example.schoology.pages.resources;

import org.example.core.ui.AbstractPage;
import org.example.schoology.pages.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class AddRubricPopup extends AbstractPage {

    @FindBy(css = "input[id='rubric-title-input']")
    private WebElement titleTextField;

    @FindBy(css = "input[placeholder='Add Title']")
    private WebElement criteriaTextField;

    @FindBy(xpath = "//*[@id='grading-rubric-edit-slider']//button")
    private WebElement submitButton;

    public Resources create(final Map<String, String> rubricMap) {
        fill(rubricMap);
        action.click(submitButton);
        return new Resources();
    }

    public void fill(final Map<String, String> rubricMap) {
        Map<String, Step> stepsMap = new HashMap<>();
        stepsMap.put("title", () -> setTitle(rubricMap.get("title")));
        stepsMap.put("criteria", () -> setCriteria(rubricMap.get("criteria")));

        for (final String keyField : rubricMap.keySet()) {
            stepsMap.get(keyField).execute();
        }
    }

    public void setTitle(final String title) {
        titleTextField.sendKeys(title);
    }

    public void setCriteria(final String criteria) {
        criteriaTextField.sendKeys(criteria);
    }
}
