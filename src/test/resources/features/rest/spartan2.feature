@database
Feature: Spartan App rest Api Up and running

  As a user ,
  I should be able to send api request to spartan rest api
  and get a valid response

  Background:
    Given the base _uri and base_path set

  Scenario Outline: Should be able to call /spartans to get all data

    And I ask for "<formatAskedFor>" response payload
    When I send get request to "/spartans" endpoint
    # And I set the Accept header to "Application/json"
    Then I should get status code 200
    Then The response format should be "<expectedContentTypeHeader>"

    Examples:
      | formatAskedFor | expectedContentTypeHeader |
      | json           | application/json          |
      | xml            | application/xml           |


  Scenario: should be able to add valid data to Spartan app
  # set the content type ,  provide the json body ( or map body) send request and verify 201
    Given I send the data in json format
    And   I am sending below valid spartan data
      | name   | Mehmet         |
      | gender | Male           |
      | phone  | 123456789456   |

    When I send post request to "/spartans" endpoint
    Then I should get status code 201
    Then the success field value is A Spartan is Born!
  ## step to accept jsonPath and check for value equality
    Then the field value for "success" path should be equal to "A Spartan is Born!"
    Then the field value for "data.name" path should be equal to "Mehmet"
    Then the field value for "data.gender" path should be equal to "Male"
    Then the field value for "data.phone.toLong()" path should be equal to 123456789456
      #  or you can turn it into String and check string equality
    Then the field value for "data.phone.toString()" path should be equal to "123456789456"


#  Scenario: Should be able to Get valid response for GET /hello endpoint
#
#    Given the base _uri and base_path set
#    When I send get request to "/hello" endpoint
#    Then I should get status code 200
#    And The response format should be "text/plain;charset=UTF-8"
#    And The response payload should be Hello from Sparta

#  Scenario: Should be able to call /spartans to get all data
#
#    Given the base _uri and base_path set
#    And I ask for "json" response payload
#    When I send get request to "/spartans" endpoint
#    # And I set the Accept header to "Application/json"
#    Then I should get status code 200
#    Then The response format should be "application/json"
#
#  Scenario: Should be able to call /spartans to get all data
#
#    Given the base _uri and base_path set
#    And I ask for "xml" response payload
#    When I send get request to "/spartans" endpoint
#    # And I set the Accept header to "Application/json"
#    Then I should get status code 200
#    Then The response format should be "application/xml"