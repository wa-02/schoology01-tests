# new feature
# Tags: optional

Feature: Add Material for a course
  @deleteCourse
  Scenario: Verify that an student can show an folder create for a instructor
    Given I am a "Instructor01" of:
      | name    | Physical 101 |
      | section | New Section  |
      | area    | Technology   |
      | level   | Graduate     |
    And "Student02" is my student
    When I as "Instructor01" user of "Physical 101" course create a "Folder" for my class
      | title   	| Home works   |
      | color   	| red		   |
      | description	| Technology   |
      | availability| Published    |
    Then "Student02" should have a "Home works" folder in "Instructor01"'s "Physical 101" class.
