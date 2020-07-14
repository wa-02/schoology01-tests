# new feature
# Tags: optional

Feature: Tests for Resources section

  @deleteRubric
  Scenario: Create a new Rubric
    Given I login as "Instructor01" user
    And I create a rubric with:
      | title       | RGRubric      |
      | criteria    | Criteria RG   |
    Then I should see the rubric "RGRubric" in list