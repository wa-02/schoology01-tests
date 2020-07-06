package org.example.schoology.pages;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.By;

public class SubMenu extends AbstractPage {

    public void clickViewListLink(final String menu) {
        action.click(By.cssSelector(String.format("a[href='/%s']", menu.toLowerCase())));
    }

}
