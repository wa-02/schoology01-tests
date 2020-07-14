package org.example.schoology.pages;

import org.example.core.ui.AbstractPage;
import org.example.schoology.pages.resources.Resources;
import org.openqa.selenium.By;

public class SubMenuTemplate extends AbstractPage {

    public static final String RESOURCE_PAGE = "//a[text()='Resources']";

    public Resources clickResourcesMenu() {
        action.click(By.xpath(RESOURCE_PAGE));
        return new Resources();
    }

    public SubMenu clickMenu(final String menuName) {
        action.click(By.xpath(String.format("//span[text()='%s']/parent::button", menuName)));
        return new SubMenu();
    }
}
