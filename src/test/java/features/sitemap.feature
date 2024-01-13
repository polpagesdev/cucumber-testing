Feature: Sitemap

  Scenario: User checks if all categories are listed in the site map
    Given the user is set up
    And the user is on the "content/sitemap" page
    Then the user should see all categories listed

  Scenario: User checks if links in the site map are functional
    Given the user is set up
    And the user is on the "content/sitemap" page
    And the user scrolls 50 % of the page
    When the user clicks the "https://automationteststore.com/index.php?rt=product/category&path=49" button [with tag "a" and attribute "href"]
    Then the user should be redirected to the "product/category&path=49" page
