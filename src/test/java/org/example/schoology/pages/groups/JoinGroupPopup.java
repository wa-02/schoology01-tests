package org.example.schoology.pages.groups;

import org.example.schoology.pages.courses.Course;

import java.util.Map;

public class JoinGroupPopup extends AbstractGroupPopup {

    public Group join(final String  accessCodeValue) {
        fillAccessCode(accessCodeValue);
        submitButton.click();
        return new Group();
    }



}
