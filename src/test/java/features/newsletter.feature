Feature: Newsletter

  Scenario: User subscribes to the newsletter
    Given the user is set up
    And the user scrolls 100 % of the page
    And the user clicks the "btn btn-orange" button [with tag "button" and attribute "class"]
    And the user clicks the "settings[newsletter][email]" button [with tag "input" and attribute "name"]
    And the user clicks the "Continue" button [with tag "button" and attribute "title"]
    And the user waits "1" seconds
    Then the user should receive a "alert alert-success" message [with tag "div" and attribute "class"]

  Scenario: User unsubscribes from the newsletter
    Given the user is set up
    And the user scrolls 100 % of the page
    And the user clicks the "btn btn-orange" button [with tag "button" and attribute "class"]
    And the user clicks the "settings[newsletter][email]" button [with tag "input" and attribute "name"]
    And the user clicks the "Continue" button [with tag "button" and attribute "title"]
    And the user waits "1" seconds
    Then the user should receive a "alert alert-success" message [with tag "div" and attribute "class"]