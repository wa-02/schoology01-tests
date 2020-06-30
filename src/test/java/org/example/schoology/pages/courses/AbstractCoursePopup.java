package org.example.schoology.pages.courses;

import java.util.HashMap;
import java.util.Map;

import org.example.core.ui.AbstractPage;
import org.example.schoology.pages.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public abstract class AbstractCoursePopup extends AbstractPage {

    @FindBy(css = "#edit-course-name")
    private WebElement courseNameTextField;

    @FindBy(css = "#edit-section-name-1")
    private WebElement sectionNameTextField;

    @FindBy(css = "#edit-subject-area")
    private WebElement subjectAreaDropDown;

    @FindBy(css = "#edit-grade-level-range-start")
    private WebElement levelDropDown;

    @FindBy(css = "#edit-submit")
    protected WebElement submitButton;

    public void fill(final Map<String, String> courseMap) {
        Map<String, Step> stepsMap = new HashMap<>();
        stepsMap.put("name", () -> setName(courseMap.get("name")));
        stepsMap.put("section", () -> setSection(courseMap.get("section")));
        stepsMap.put("area", () -> selectSubjectArea(courseMap.get("area")));
        stepsMap.put("level", () -> selectLevel(courseMap.get("level")));

        for (final String keyField : courseMap.keySet()) {
            stepsMap.get(keyField).execute();
        }
    }

    public void setName(final String name) {
        courseNameTextField.sendKeys(name);
    }

    private void setSection(final String section) {
        WebElement sectionField = sectionNameTextField;
        sectionField.clear();
        sectionField.sendKeys(section);
    }

    public void selectSubjectArea(final String area) {
        Select subjectArea = new Select(subjectAreaDropDown);
        subjectArea.selectByVisibleText(area);
    }

    public void selectLevel(final String level) {
        Select levelField = new Select(levelDropDown);
        levelField.selectByVisibleText(level);
    }
}
