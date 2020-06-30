package org.example.schoology.pages.groups;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateGroupPopup extends AbstractGroupPopup {

    public Group create(final Map<String, String> groupMap) {
        fill(groupMap);
        // It is possible to add an explicit wait here.
        action.click(submitButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-submit")));
        return new Group();
    }
}
