Feature: Groups

  @deleteGroupInstructor1
  Scenario: Instructor B is able to join a group of Instructor A
    Given I log in as "Instructor01" user
    And I create a group with:
      | name        | SeleniumWebDriverJoin |
      | description | Description       |
      | privacy     | School            |
      | access      | Invite Only       |
      | category    | Musical Groups    |
    And I save its Access Code
    And I log in as "Instructor02" user
    When I navigate to "Groups"
    And I join in a group with Access code
    Then I should see the "You have successfully joined the group." message
    And I should see a group with "SeleniumWebDriverJoin" as a name

  @deleteGroupInstructor1
  Scenario: Instructor B that has joined in a group of Instructor A and he is able to add a post
    Given I log in as "Instructor01" user
    And I create a group with:
      | name        | SeleniumWebDriverJoin |
      | description | Description       |
      | privacy     | School            |
      | access      | Invite Only       |
      | category    | Musical Groups    |
    And I save its Access Code
    And I log in as "Instructor02" user
    When I navigate to "Groups"
    And I join in a group with Access code
    And I access to "SeleniumWebDriverJoin" Group detail
    And I add a post "Comment of instructor B"
    Then I should see a post with with "Comment of instructor B" as description

  @deleteCourse
  Scenario: Instructor A is not able to join in a group with Course access code
    Given I log in as "Instructor01" user
    And I create a course with:
      | name    | SeleniumWebDriverJoin |
      | section | New Section       |
      | area    | Mathematics       |
      | level   | Undergraduate     |
    And I save its Access Code
    When I navigate to "Groups"
    And I join in a group with Access code
    Then I should see the "The access code you entered is not a group access code." message
    And I should not see a group with " SeleniumWebDriverJoin" as a name

