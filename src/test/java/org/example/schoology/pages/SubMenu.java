package org.example.schoology.pages;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class SubMenu extends AbstractPage {
    public static final Integer TIME_TO_WAIT = 5;

    public void clickViewListLink(final String menu) {
        driver.manage().timeouts().implicitlyWait(TIME_TO_WAIT, TimeUnit.SECONDS);
        action.click(By.cssSelector(String.format("a[href='/%s']", menu.toLowerCase())));
    }

}
