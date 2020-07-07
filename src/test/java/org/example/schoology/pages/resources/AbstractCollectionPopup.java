package org.example.schoology.pages.resources;

import org.example.core.ui.AbstractPage;
import org.example.schoology.pages.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class AbstractCollectionPopup extends AbstractPage {


    @FindBy(css = "#edit-title")
    protected WebElement collectionTitleField;

    @FindBy(css = "input[value=\"Create\"]")
    protected WebElement submitButton;


    public void fill(Map<String, String> collectionMap){
        Map<String, Step> stepsMap = new HashMap<>();
        stepsMap.put("title", () -> setTitle(collectionMap.get("title")));

        for(String keyField: collectionMap.keySet()){
            stepsMap.get(keyField).execute();
        }
    }

    private void setTitle(final String title){
        collectionTitleField.clear();
        collectionTitleField.sendKeys(title);
    }


}
