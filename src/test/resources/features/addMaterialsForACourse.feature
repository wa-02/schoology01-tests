# new feature
# Tags: optional

Feature: Add Material for a course

  @deleteCourse
  Scenario: Verify that an student can show an folder create for an instructor
    Given I am a "Instructor01" of:
      | name    | Physical 101 |
      | section | New Section  |
      | area    | Technology   |
      | level   | Graduate     |
    And "Student02" is my student
    When I as "Instructor01" user of "Physical 101" course create a "Folder" for my class
      | title        | Home works |
      | color        | red        |
      | description  | Technology |
#      | date	    | 7/07/20      |  TODO: Popup date is not a webElement constant
      | availability | Published  |
    Then "Student02" should have a "Home works" material in "Instructor01"'s "Physical 101" class.


  @deleteCourse
  Scenario: Verify that an student can show an assignment create for an instructor
    Given I am a "Instructor01" of:
      | name    | Physical 200 |
      | section | New Section  |
      | area    | Technology   |
      | level   | Graduate     |
    And "Student02" is my student
    When I as "Instructor01" user of "Physical 200" course create a "Assignment" for my class
      | name        | New assignment |
      | description | New assignment |
      | category    | Homework       |
    Then "Student02" should have a "New assignment" material in "Instructor01"'s "Physical 200" class.


  @deleteCourse
  Scenario: Verify that an student can show an link-document create for an instructor
    Given I am a "Instructor01" of:
      | name    | Physical 300 |
      | section | New Section  |
      | area    | Technology   |
      | level   | Graduate     |
    And "Student02" is my student
    When I as "Instructor01" user of "Physical 300" course create a "Document" for my class
      | link      | https://www.youtube.com/ |
      | linkTitle | youtube                  |
    Then "Student02" should have a "youtube" material in "Instructor01"'s "Physical 300" class.

  @deleteCourse
  Scenario: Verify that an student can see a Discussion created by an instructor
    Given I am a "Instructor01" of:
      | name    | Art 100           |
      | section | Art Section       |
      | area    | Special Education |
      | level   | Graduate          |
    And "Student01" is my student
    When I as "Instructor01" user of "Art 100" course create a Discussion for my class with:
      | title       | What is Art    |
      | description | Talk about Art |
    Then "Student01" should have a "What is Art" material in "Instructor01"'s "Art 100" class.