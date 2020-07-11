package org.example.schoology.pages.courses;

import org.example.schoology.pages.AbstractDetail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Course extends AbstractDetail {

    private final By cssCourseProfile = By.cssSelector("#course-profile-materials");

    @FindAll({
            @FindBy(css = "#course-profile-materials"),
            @FindBy(css = "#cursos-profile-materials")
    })
    private WebElement courseProfileMaterials;

    @FindBy(css = "div.course-member-left-menu")
    private WebElement membersButton;

    public Course() {
        wait.until(ExpectedConditions.visibilityOf(courseProfileMaterials));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cssCourseProfile));
    }

    public Members clickMembers() {
        membersButton.click();
        return new Members();
    }
}
