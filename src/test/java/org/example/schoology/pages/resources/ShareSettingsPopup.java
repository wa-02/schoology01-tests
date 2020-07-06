package org.example.schoology.pages.resources;

import org.example.core.Environment;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShareSettingsPopup {

    @FindBy(css = "#edit-people-search")
    private WebElement searchTeacher;

    @FindBy(css = "#uid-93464863-wrapper")
    private WebElement selectTeacher;

    @FindBy(css = "edit-submit-1")
    private WebElement addPeopleButton;


    public Resources searchTeacher(String name){
        searchTeacher.sendKeys(Environment.getInstance().getValue(String.format("credentials.%s.name", name)));
        selectTeacher.click();
        addPeopleButton.click();
        return new Resources();

    }
}
