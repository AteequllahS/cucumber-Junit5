Feature: Spartan App rest Api Up and running

  As a user ,
  I should be able to send api request to spartan rest api
  and get a valid response

  Scenario: Should be able to Get valid response for GET /hello endpoint

    Given the base _uri and base_path set
    When I send get request to "/hello" endpoint
    Then I should get status code 200
    And The response format should be "text/plain;charset=UTF-8"
    And The response payload should be Hello from Sparta

  Scenario: Should be able to call /spartans to get all data

    Given the base _uri and base_path set
    And I ask for "json" response payload
    When I send get request to "/spartans" endpoint
    # And I set the Accept header to "Application/json"
    Then I should get status code 200
    Then The response format should be "application/json"

  Scenario: Should be able to call /spartans to get all data

    Given the base _uri and base_path set
    And I ask for "xml" response payload
    When I send get request to "/spartans" endpoint
    # And I set the Accept header to "Application/json"
    Then I should get status code 200
    Then The response format should be "application/xml"