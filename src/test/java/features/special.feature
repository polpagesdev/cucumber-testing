Feature: Special Offers

  Scenario: User checks if a product is on sale
    Given the user is set up
    And the user is on the "product/special" page
    Then the user should receive a "sale" message [with tag "span" and attribute "class"]

  Scenario: User adds a sale product to the shopping cart and checks discount
    Given the user is set up
    And the user is on the "product/special" page
    When the user clicks the "Absolue Eye Precious Cells" button [with tag "a" and attribute "title"]
    Then the price should be the same after adding the product to the cart
