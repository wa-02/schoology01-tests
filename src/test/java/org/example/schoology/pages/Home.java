package org.example.schoology.pages;

import org.example.core.ui.AbstractPage;
import org.example.schoology.pages.resources.Resources;
import org.openqa.selenium.By;

public class Home extends AbstractPage {

    /**
     * This only works for Courses and Groups
     * Resources and More menu has another behavior.
     *
     * @param menuName {courses or groups}
     * @return {@link SubMenu}
     */

    public static final String RESOURCE_PAGE = "//a[text()='Resources']";

    public static final String OPTIONS_MENU = "//div[@data-sgy-sitenav=\"header-my-account-menu\"]";



    public SubMenu clickMenu(final String menuName) {
        action.click(By.xpath(String.format("//span[text()='%s']/parent::button", menuName)));
        return new SubMenu();
    }

    public Resources clickResourcesMenu() {
        action.click(By.xpath(RESOURCE_PAGE));
        return new Resources();
    }

    public InfoPage selectYourProfileAccount() {
        action.click(By.xpath(OPTIONS_MENU));
        action.click(By.xpath(("//a[text()='Your Profile']")));
        return new InfoPage();
    }
}
