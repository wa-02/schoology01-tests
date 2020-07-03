package org.example.schoology.pages.groups;


public class JoinGroupPopup extends AbstractGroupPopup {

    public Group join(final String  accessCodeValue) {
        fillAccessCode(accessCodeValue);
        submitButton.click();
        return new Group();
    }

}
