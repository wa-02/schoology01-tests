package org.example.schoology.pages;

import org.example.core.ui.AbstractPage;
import org.example.schoology.pages.courses.Badges;
import org.openqa.selenium.By;

public class InfoPage extends AbstractPage {


    public Badges clickBadgesEstudentTab() {
        action.click(By.xpath("//a[text()=\"Badges\"]"));
        return new Badges();
    }
}
