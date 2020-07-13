Feature: Resources

  @deleteResourceCollection
  Scenario: Share a resource Collection to a School colleague
    Given I login as "Instructor02" user
    And I create a resource collection with:
      | title | RCollectionName01 |
    When I share the "RCollectionName01" collection with "Instructor01"
    And I log in as "Instructor01" user
	And I navigate to Resources
    Then I should see the "RCollectionName01" title collection


