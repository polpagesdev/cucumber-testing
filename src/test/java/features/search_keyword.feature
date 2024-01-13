Feature: Search by Keyword

  Scenario: User searches for a specific product category
    Given the user is set up
    When the user clicks the "button-in-search" button [with tag "div" and attribute "class"]
    When the user selects the option "0,49" [with attribute "id" and value "category_id"]
    And the user inputs "ck one" in "keyword" [with tag "input" and attribute "id"]
    And the user clicks the "search_button" button [with tag "button" and attribute "id"]
    Then the user should receive a "prdocutname" message [with tag "a" and attribute "class"]

  Scenario: User searches for a specific product model
    Given the user is set up
    When the user clicks the "button-in-search" button [with tag "div" and attribute "class"]
    When the user clicks the "model" button [with tag "input" and attribute "name"]
    And the user inputs "601232" in "keyword" [with tag "input" and attribute "id"]
    And the user clicks the "search_button" button [with tag "button" and attribute "id"]
    Then the user should receive a "productdesc" message [with tag "div" and attribute "id"]

  Scenario: User searches using descriptive terms
    Given the user is set up
    When the user clicks the "button-in-search" button [with tag "div" and attribute "class"]
    When the user clicks the "description" button [with tag "input" and attribute "id"]
    And the user inputs "masculine tobacco musk" in "keyword" [with tag "input" and attribute "id"]
    And the user clicks the "search_button" button [with tag "button" and attribute "id"]
    Then the user should receive a "productdesc" message [with tag "div" and attribute "id"]

  Scenario: User searches with terms that yield no results
    Given the user is set up
    When the user clicks the "button-in-search" button [with tag "div" and attribute "class"]
    And the user inputs "noresults" in "keyword" [with tag "input" and attribute "id"]
    And the user clicks the "search_button" button [with tag "button" and attribute "id"]
    Then the user should receive a "heading4" message [with tag "h4" and attribute "class"]
