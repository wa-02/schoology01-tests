package org.example.schoology.pages.courses;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Materials extends AbstractPage {

    @FindBy(css = "div.course-content-action-links")
    private WebElement addMaterialsButton;

    @FindBy(css = "li.action-create-folder")
    private WebElement addFolderButton;

    public CreateFolderPopup clickAddFolder() {
        action.click(addMaterialsButton);
        action.click(addFolderButton);
        return new CreateFolderPopup();
    }
}
