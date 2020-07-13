package org.example.schoology.pages;

import org.example.core.ui.AbstractPage;
import org.example.schoology.pages.courses.Course;
import org.openqa.selenium.By;

public class SubMenu extends AbstractPage {

    public void clickViewListLink(final String menu) {
        action.click(By.cssSelector(String.format("a[href='/%s']", menu.toLowerCase())));
    }

    public Course clickCourseSection(final String sectionName) {
        action.click(By.xpath(String.format("//a[text()='%s']", sectionName)));
        return new Course();
    }

}
