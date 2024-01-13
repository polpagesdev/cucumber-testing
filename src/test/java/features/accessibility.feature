Feature: Accessibility

  Scenario: User changes the currency
    Given the user is set up
    And the user "adds" an item in their cart
    And the user is on the "checkout/cart" page
    When the user selects the "EUR" currency
    Then prices should be displayed in "EUR"
