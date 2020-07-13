package org.example.schoology.pages.resources;

import org.example.schoology.pages.SubMenuTemplate;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

public class AddTemplatePopup extends AbstractResourcePopup {

    public SubMenuTemplate create(final Map<String, String> datatable) {
        fill(datatable);
        action.click(submitButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=\"Add Template\"]")));
        return new SubMenuTemplate();
    }
}
