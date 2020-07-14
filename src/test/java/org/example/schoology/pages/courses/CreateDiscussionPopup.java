package org.example.schoology.pages.courses;

import org.example.core.ui.AbstractPage;
import org.example.schoology.pages.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.HashMap;
import java.util.Map;

public class CreateDiscussionPopup extends AbstractPage {

    @FindBy(css = "#edit-title")
    private WebElement titleTextField;

    @FindBy(css = "#tinymce")
    private WebElement descriptionTextField;

    @FindBy(css = "#edit-submit")
    protected WebElement submitButton;

    public void fill(final Map<String, String> discussionData) {
        Map<String, Step> stepsMap = new HashMap<>();
        stepsMap.put("title", () -> setTitle(discussionData.get("title")));
        stepsMap.put("description", () -> setDescription(discussionData.get("description")));

        for (final String keyField : discussionData.keySet()) {
            stepsMap.get(keyField).execute();
        }
    }

    public void setTitle(final String title) {
        titleTextField.sendKeys(title);
    }

    private void setDescription(final String description) {
        driver.switchTo().frame("edit-post_ifr");
        driver.findElement(By.xpath("//body[contains(@class, 'mceContentBody')]")).click();
        driver.findElement(By.xpath("//body[contains(@class, 'mceContentBody')]/child::p")).sendKeys(description);
        driver.switchTo().defaultContent();
    }

    public Course createDiscussion(final Map<String, String> discussionData) {
        fill(discussionData);
        submitButton.submit();
        return new Course();
    }
}
