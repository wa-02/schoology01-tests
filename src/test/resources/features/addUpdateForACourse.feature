# new feature
# Tags: optional

Feature: Add Update to course
  @deleteCourse
  Scenario: Verify that an student can show an update creates by an instructor
    Given I am a "Instructor01" of:
      | name    | Logic 101   |
      | section | Section101  |
      | area    | Science     |
      | level   | Graduate    |
    And "Student01" is my student
    When I add an update to "Logic 101" course as "Instructor01" with "Do not forget upload your tasks"
    Then "Student01" should see the update in "Logic 101" class
    Then "Student01" should have a "What is Art" material in "Instructor01"'s "Logic 101" class.