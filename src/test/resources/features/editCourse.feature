Feature: Courses

  @deleteCourse
  Scenario: Edit a course
    Given I log in as "Instructor01" user
    And I create a course with:
      | name    | SeleniumWebDriver |
      | section | New Section       |
      | area    | Mathematics       |
      | level   | Undergraduate     |
    When I navigate to "Courses"
    And I edit the "SeleniumWebDriver" course with:
      | section | Section Test |
      | area    | Science      |
    Then I should see the "The section has been updated." message
    And I should see the "Section Test" section on "SeleniumWebDriver" course item
