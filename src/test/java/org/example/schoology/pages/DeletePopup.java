package org.example.schoology.pages;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeletePopup extends AbstractPage {

    @FindBy(css = "#edit-submit")
    private WebElement deleteButton;

    public void clickDeleteButton() {
        action.click(deleteButton);
    }

}
