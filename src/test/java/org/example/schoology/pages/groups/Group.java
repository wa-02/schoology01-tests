package org.example.schoology.pages.groups;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Group extends AbstractPage {

    private final By cssGroupProfile = By.cssSelector(".action-links-unfold-text");

    public Group() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cssGroupProfile));
    }
}
