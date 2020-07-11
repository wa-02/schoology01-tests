package org.example.schoology.pages;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.By;


public class SubMenu extends AbstractPage {

    public static final String XPATH_VIEW_LIST_LINK =
            "a[href='/%s']";

    public void clickViewListLink(final String menuName) {
        if (menuName == "Courses") {
            new CourseAvailableAudioAndVideoItemsPopup().closeCourseAvailableItemsPopup();
        }
        action.click(By.cssSelector(String.format(XPATH_VIEW_LIST_LINK, menuName.toLowerCase())));
    }

}
