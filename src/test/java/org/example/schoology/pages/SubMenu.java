package org.example.schoology.pages;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class SubMenu extends AbstractPage {

    public static final String XPATH_ITEM_ACTION_BUTTON =
            "//*[text()='%s']/parent::div/parent::div/parent::a";


    public void clickViewListLink(final String menu) {
        action.click(By.cssSelector(String.format("a[href='/%s']", menu.toLowerCase())));
    }

    public void clickItemCreatedLink(final String nameItem) {
        WebElement groupActionsButton = driver.findElement(By.xpath(String.format(XPATH_ITEM_ACTION_BUTTON, nameItem)));
        action.click(groupActionsButton);
    }

}
