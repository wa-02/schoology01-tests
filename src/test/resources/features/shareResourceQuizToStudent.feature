Feature: Resources

  Scenario: Share a resource Quiz add to a course with a Student
    Given I log in as "Instructor01" user
    And I create a course with:
      | name    | TechCourse	    |
      | section | New Section       |
      | area    | Technology        |
      | level   | Graduate          |
    And I create a "Test/Quiz" resource with:
      | name       | Quiz01   |
      | Max points | 75       |
    When I add the "Quiz01" to the "TechCourse"
    And I log in as "Student01" user
    And I join to the course created
    Then I should see the resource shared with the course
    And I should be able to open the quiz..