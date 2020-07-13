package org.example.schoology.pages.groups;

public class JoinGroupPopup extends AbstractGroupPopup {

    public Group join(final String  accessCodeValue) {
        fillAccessCode(accessCodeValue);
        action.click(submitButton);
        return new Group();
    }

}

