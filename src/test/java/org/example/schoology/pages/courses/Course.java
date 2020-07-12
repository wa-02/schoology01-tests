package org.example.schoology.pages.courses;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Course extends AbstractPage {

    private final By cssCourseProfile = By.cssSelector("#course-profile-materials");

    @FindAll({
            @FindBy(css = "#course-profile-materials"),
            @FindBy(css = "#cursos-profile-materials")
    })
    private WebElement courseProfileMaterials;

    @FindBy(css = ".enrollment-code")
    private WebElement accessCode;

    @FindBy(css = "div.course-member-left-menu")
    private WebElement membersButton;

    @FindBy(css = "div.course-materials-left-menu")
    private WebElement materialsButton;

    public Course() {
        wait.until(ExpectedConditions.visibilityOf(courseProfileMaterials));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cssCourseProfile));
    }

    public String getAccessCode() {
        return accessCode.getText();
    }

    public Members clickMembers() {
        membersButton.click();
        return new Members();
    }

    public Materials clickMaterials() {
        materialsButton.click();
        return new Materials();
    }
}
