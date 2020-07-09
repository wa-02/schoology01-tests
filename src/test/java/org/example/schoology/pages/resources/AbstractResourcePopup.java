package org.example.schoology.pages.resources;

import org.example.core.ui.AbstractPage;
import org.example.schoology.pages.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class AbstractResourcePopup extends AbstractPage {


    @FindBy(css = "#edit-title")
    protected WebElement collectionTitleField;

    @FindBy(css = "input[value=\"Create\"]")
    protected WebElement submitButton;

    @FindBy(css = "#edit-template-fields-title")
    protected WebElement nameQuizTexField;

    @FindBy(css = "#edit-template-fields-max-points")
    protected WebElement maxPointQuizTextField;


    public void fill(final Map<String, String> collectionMap) {
        Map<String, Step> stepsMap = new HashMap<>();
        stepsMap.put("title", () -> setTitle(collectionMap.get("title")));
        stepsMap.put("nameQuiz", () -> setNameQuiz(collectionMap.get("nameQuiz")));
        stepsMap.put("maxPointQuiz", () -> setMaxPointQuiz(collectionMap.get("maxPointQuiz")));


        for (String keyField: collectionMap.keySet()) {
            stepsMap.get(keyField).execute();
        }
    }

    private void setTitle(final String title) {
        collectionTitleField.clear();
        collectionTitleField.sendKeys(title);
    }

    private void setNameQuiz(final String nameQuiz) {
        nameQuizTexField.clear();
        nameQuizTexField.sendKeys(nameQuiz);
    }

    private void setMaxPointQuiz(final String maxPointQuiz) {
        maxPointQuizTextField.clear();
        maxPointQuizTextField.sendKeys(maxPointQuiz);
    }


}

