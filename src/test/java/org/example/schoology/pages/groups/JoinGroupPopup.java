package org.example.schoology.pages.groups;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class JoinGroupPopup extends AbstractGroupPopup {

    public Group join(final String  accessCodeValue) {
        fillAccessCode(accessCodeValue);
        action.click(submitButton);
        return new Group();
    }

}
