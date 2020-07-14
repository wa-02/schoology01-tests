package org.example.schoology.pages;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class SubMenu extends AbstractPage {

    public void clickViewListLink(final String menu) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        action.click(By.cssSelector(String.format("a[href='/%s']", menu.toLowerCase())));
    }

}
