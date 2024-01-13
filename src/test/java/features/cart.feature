Feature: Cart

  Scenario: User changes the quantity of an item in their cart
    Given the user is set up
    And the user "adds" an item in their cart
    And the user is on the "checkout/cart" page
    When the user "increases" the item quantity
    Then the cart should update the total price accordingly

  Scenario: User applies a coupon code
    Given the user is set up
    And the user "adds" an item in their cart
    And the user is on the "checkout/cart" page
    When the user inputs "test" in "coupon" [with tag "input" and attribute "name"]
    And the user clicks the "Apply Coupon" button [with tag "button" and attribute "title"]
    Then the user should receive a "alert alert-error alert-danger" message [with tag "div" and attribute "class"]

  Scenario: User removes an item from their cart
    Given the user is set up
    And the user "adds" an item in their cart
    And the user is on the "checkout/cart" page
    When the user "removes" an item in their cart
    Then the cart should no longer contain the item
