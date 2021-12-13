@ui
Feature: Simple navigation in Spartan App

  As a user ,
  I should be able to navigate through spartan app
  to go to correct pages

  Scenario: Should be able to view hope page and All spartan page

    Given user navigate to home page
    Then user should see below links
      | Web Data |
      | API Doc  |
      | Database |