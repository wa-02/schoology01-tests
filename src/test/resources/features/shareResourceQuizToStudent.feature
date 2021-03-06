Feature: Resources

  @deleteCourse
  @deleteResource
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
    When I add the "Quiz01" to the course created
    And "Student01" user use the "AccessCode"
    Then I should see the "Quiz01" resource of my "Instructor01" in my course list