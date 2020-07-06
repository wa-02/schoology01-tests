Feature: Resources

  Scenario: Share a resource Collection to a College's colleague
    Given I log in as "Instructor02" user
    And I create a resource collection with:
      | title | ResourceCollectionName01 |
    When I share the "ResourceCollectionName01" collection with "Instructor01"
    And I log in as "Instructor01" user
    And I navigate to "Resources"
    Then I should see the "ResourceCollectionName01" title collection
