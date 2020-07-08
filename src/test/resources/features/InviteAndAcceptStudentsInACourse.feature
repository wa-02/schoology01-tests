# new feature
# Tags: optional

Feature: A teacher invite and accept students in its course
  @deleteCourse
  Scenario: Invite and accept students in a course
    Given I am a "Instructor01" of:
      | name    | Physical 100 |
      | section | New Section  |
      | area    | Technology   |
      | level   | Graduate     |
    And I have the course code
    When "Student01" user use the "AccessCode"
    Then I am as "Instructor01" should have a "Student01" user in the "Physical 100" course
