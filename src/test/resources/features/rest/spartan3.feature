@database
Feature: Spartan API Single Data

  As a user ,
  I should be able to get individual data
  using valid id and see details ,  update the value or delete the whole data

  Background:
    Given the base _uri and base_path set


    #get single data
  Scenario: Should be able to get single spartan using /spartans/{id}
    And I have valid spartan id
    And I ask for "json" response payload
    When I send get request to "/spartans/{id}" endpoint
    Then I should get status code 200
    Then the spartan data with that id matches value in the database


  Scenario: Should be able to delete single spartan using DELETE /spartans/{id}
    And I have valid random spartan id
    When I send delete request to "/spartans/{id}" endpoint
    Then I should get status code 204
    When I send get request to "/spartans/{id}" endpoint
    Then I should get status code 404


# delete request
  Scenario: Should be able to delete single spartan using DELETE /spartans/{id}
    And I have valid random spartan id
    And I send the data in json format
    And I am sending below valid spartan data
      | name   | Mehmet         |
      | gender | Male           |
      | phone  | 123456789456   |
    When I send put request to "/spartans/{id}" endpoint
    Then I should get status code 204
    When I send get request to "/spartans/{id}" endpoint
    Then the field value for "name" path should be equal to "Mehmet"
    Then the field value for "gender" path should be equal to "Male"
    Then the field value for "phone.toString()" path should be equal to "123456789456"

# patch request (Partial update
  Scenario: Should be able to partial update single spartan using DELETE /spartans/{id}
    And I have valid random spartan id
    And I send the data in json format
    And I am sending below valid spartan data
      | name   | Cucumber        |
      | gender | Male           |
    When I send patch request to "/spartans/{id}" endpoint
    Then I should get status code 204
    When I send get request to "/spartans/{id}" endpoint
    Then the field value for "name" path should be equal to "Cucumber"
    Then the field value for "gender" path should be equal to "Male"

