Feature: Products

  Scenario: User views a product's description
    Given the user is set up
    And the user scrolls 50 % of the page
    When the user clicks the "Absolue Eye Precious Cells" button [with tag "a" and attribute "title"]
    Then the user should receive a "col-md-12 productdesc" message [with tag "div" and attribute "class"]

  Scenario: User checks the stock status of a product
    Given the user is set up
    And the user is on the "product/category&path=68" page
    And the user scrolls 50 % of the page
    When the user clicks the "Jersey Cotton Striped Polo Shirt" button [with tag "a" and attribute "title"]
    Then the user should receive a "nostock" message [with tag "span" and attribute "class"]

  Scenario: User adds a product to their wishlist
    Given the user is set up
    And the user is on the "product/special" page
    When the user clicks the "Absolue Eye Precious Cells" button [with tag "a" and attribute "title"]
    And the user clicks the "wishlist_add btn btn-large" button [with tag "a" and attribute "class"]
    And the user waits "1" seconds
    When the user is on the "account/wishlist" page
    Then the user should receive a "btn btn-sm btn-default btn-remove" message [with tag "a" and attribute "class"]

  Scenario: User removes a product to their wishlist
    Given the user is set up
    When the user is on the "account/wishlist" page
    And the user clicks the "btn btn-sm btn-default btn-remove" button [with tag "a" and attribute "class"]
    And the user waits "1" seconds
    When the user clicks the "btn btn-default mr10" button [with tag "a" and attribute "class"]
    And the user is on the "account/wishlist" page
    Then the user should receive a "contentpanel" message [with tag "div" and attribute "class"]
