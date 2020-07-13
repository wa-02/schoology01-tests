package org.example.schoology.pages.courses;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Materials extends AbstractPage {

    public static final String CSS_ADD_MATERIALS = "li.action-create-%s";

    @FindBy(css = "div.course-content-action-links")
    private WebElement addMaterialsButton;

    @FindBy(css = "li.action-create-folder")
    private WebElement addFolderButton;

    @FindBy(css = ".folder-contents-cell div div div a")
    private WebElement materialsNameLabel;

    @FindBy(css = ".action-create-link")
    private WebElement linkButton;



    public CreateMaterialPopup clickAddMaterials(final String materialName) {
        action.click(addMaterialsButton);
        WebElement material = driver.findElement(By.cssSelector(
                String.format(CSS_ADD_MATERIALS, materialName.toLowerCase())));
        material.click();

        if (materialName.toLowerCase().equals("document")) {
            action.click(linkButton);
        }

        return new CreateMaterialPopup();
    }

    public String getMaterial() {
        return materialsNameLabel.getText();
    }
}
