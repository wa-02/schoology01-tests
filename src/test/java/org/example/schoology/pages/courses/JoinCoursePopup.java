package org.example.schoology.pages.courses;

import java.util.HashMap;
import java.util.Map;

public class JoinCoursePopup extends AbstractCoursePopup {
    public Courses join(final String code) {
        Map<String, String> courseMap = new HashMap<>();
        courseMap.put("accessCode", code);
        fill(courseMap);
        submitButton.click();
        return new Courses();
    }

}
