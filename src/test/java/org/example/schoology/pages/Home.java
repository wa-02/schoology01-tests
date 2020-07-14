package org.example.schoology.pages;

import org.example.core.ui.AbstractPage;
import org.example.schoology.pages.resources.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class Home extends AbstractPage {

    /**
     * This only works for Courses and Groups
     * Resources and More menu has another behavior.
     *
     * @param menuName {courses or groups}
     * @return {@link SubMenu}
     */
    public SubMenu clickMenu(final String menuName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[text()='%s']/parent::button", menuName))));
        action.click(By.xpath(String.format("//span[text()='%s']/parent::button", menuName)));
        return new SubMenu();
    }

    public Resources clickResourcesMenu() {
        action.click(By.xpath("//a[text()='Resources']"));
        return new Resources();
    }
}
