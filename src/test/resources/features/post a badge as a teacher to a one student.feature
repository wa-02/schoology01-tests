Feature: Post a badge

  @deleteCourse
  Scenario: post a badge as a teacher to a one student
     Given I log in as "Instructor01" user
     And I create a course with:
      | name    | CourseBadge       |
      | section | New Section       |
      | area    | Technology        |
      | level   | Graduate          |
     And I have the course code
     When "Student01" user use the "AccessCode"
     And I log in as "Instructor01" user
     And I create a badged of "Perfect Attendance"
     And I add the badged to "Student01"
     And I log in as "Student01" user
     And I go to my badged section in my profile
     Then I should see "Perfect Attendance" badged