package org.example.schoology.pages;

import org.example.core.ui.AbstractPage;
import org.example.schoology.pages.resources.Resources;
import org.openqa.selenium.By;

public class SubMenuTemplate extends AbstractPage {

    public Resources clickResourcesMenu() {
        action.click(By.xpath("//a[text()='Resources']"));
        return new Resources();
    }

}
