Feature: Resources

  Scenario: Share a resource Quiz added to a course with a Student
    Given I log in as "Instructor01" user
    And I create a course with:
      | name    | TechCourse	    |
      | section | New Section       |
      | area    | Technology        |
      | level   | Graduate          |
    And I have the course code
    And I create a Quiz resource with:
      | name       | Quiz01   |
      | points     |    75    |
    When I add the "Quiz01" to the "TechCourse"
    And I log in as "Student01" user
    And I join to the course created
    Then I should see the resource shared with the course
    And I should be able to open the quiz..