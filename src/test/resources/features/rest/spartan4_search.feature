Feature: Spartan Search Feature

  As a user,
  I should be able to filter data
  using partial name and gender


  Background:
    Given the base _uri and base_path set

  @database
  Scenario Outline: Should be able to search to get valid data using GET /spartans/search DDT

    And I search for spartan with name contains "<nameColumn>" and gender "<genderColumn>"
    When I send get request to "/spartans/search" endpoint
    Then I should get status code 200
    Then All names in the result should contain "<nameColumn>" and gender should be "<genderColumn>"
    Then The search count for name contains "<nameColumn>" and gender "<genderColumn>" should match the count in the database
    Examples:
      | nameColumn | genderColumn |
      | Ea         | Female       |
      | ab         | Male         |
      | r          | Male         |
      | Mehmet     | Male         |
      | J          | Female       |