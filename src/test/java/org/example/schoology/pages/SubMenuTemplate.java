package org.example.schoology.pages;

import org.example.core.ui.AbstractPage;
import org.example.schoology.pages.resources.Resources;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class SubMenuTemplate extends AbstractPage {
    public  static final int WAIT_TIME = 10;

    public static final String RESOURCE_PAGE = "//a[text()='Resources']";

    public Resources clickResourcesMenu() {
        action.click(By.xpath(RESOURCE_PAGE));
        return new Resources();
    }

    public SubMenu clickMenu(final String menuName) {
        driver.manage().timeouts().implicitlyWait(WAIT_TIME, TimeUnit.SECONDS);
        action.click(By.xpath(String.format("//span[text()='%s']/parent::button", menuName)));
        return new SubMenu();
    }
}
