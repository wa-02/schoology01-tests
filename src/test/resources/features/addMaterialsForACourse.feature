# new feature
# Tags: optional

Feature: A description
  @deleteCourse
  Scenario: A scenario
    Given I am a "Instructor01" of:
      | name    | Physical 101 |
      | section | New Section  |
      | area    | Technology   |
      | level   | Graduate     |
    And "Student01" is my student
    When I as "Instructor01" user of "Physical 101" course create a "Folder" for my class
      | title   	| Home works   |
      | color   	| red		   |
      | description	| Technology   |
      | date	    | 7/07/20      |
      | availability| Published    |
    Then "Student01" should have a "Home works" folder as a material of the "Physical 101" class.
