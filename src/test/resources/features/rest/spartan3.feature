@database
Feature: Spartan API Single Data

  As a user ,
  I should be able to get individual data
  using valid id and see details ,  update the value or delete the whole data

  Background:
    Given the base _uri and base_path set


  Scenario: Should be able to get single spartan using /spartans/{id}
    And I have valid spartan id
    And I ask for "json" response payload
    When I send get request to "/spartans/{id}" endpoint
    Then I should get status code 200
    Then the spartan data with that id matches value in the database