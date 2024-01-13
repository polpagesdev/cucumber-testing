Feature: Checkout

  Scenario: User tries to checkout with an empty cart
    Given the user is set up
    When the user is on the "checkout/cart" page
    Then the user should receive a "btn btn-default mr10" message [with tag "a" and attribute "class"]

  Scenario: User completes checkout with products in the cart
    Given the user is set up
    And the user "adds" an item in their cart
    And the user is on the "checkout/cart" page
    When the user clicks the "cart_checkout1" button [with tag "a" and attribute "id"]
    When the user clicks the "checkout_btn" button [with tag "button" and attribute "id"]
    And the user waits "2" seconds
    Then the user should be redirected to the "checkout/success" page
