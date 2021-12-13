Feature: Calculate numbers
as a user
if the calculator is open
I should be able to calculate number

Scenario: Add 2 numbers
Given calculator is open
When I add one with two
Then I should get three

@bla
Scenario: Add 1 numbers
Given calculator is open
When I add one with 10
Then I should get 11