package org.example.schoology.pages.resources;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteResourceCollectionPopup extends AbstractPage {

    @FindBy(css = "input[id=\"edit-submit\"][value=\"Delete\"]")
    private WebElement deleteButton;

    public void clickDeleteButton() {
        action.click(deleteButton);
    }
}
