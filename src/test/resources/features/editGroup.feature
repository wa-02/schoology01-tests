Feature: Groups

  @deleteGroup
  Scenario: Edit a group
    Given I log in as "Instructor01" user
    And I create a group with:
      | name        | SeleniumWebDriver |
      | description | Description       |
      | privacy     | School            |
      | access      | Invite Only       |
      | category    | Musical Groups    |
    When I navigate to "Groups"
    And I edit the "SeleniumWebDriver" group with:
      | name | SeleniumWebDriver updated AT|
    Then I should see the "SeleniumWebDriver updated AT has been saved." message
    And I should see a group with "SeleniumWebDriver updated AT" as a name