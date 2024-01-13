# Selenium WebDriver Test Suite for Automation Test Store
This repository contains a collection of Selenium WebDriver tests for various features of the "https://automationteststore.com/" website. The tests cover functionalities such as product browsing, user account management, shopping cart operations, order and shipping information, and more.
## Features & Scenarios
The test suite covers the following features with their corresponding scenarios:
- __Accessibility__
  - User changes the currency
- __Account__
  - User registers a new account
  - User logs in with valid credentials
  - User logs out from the account
- __Cart__
  - User changes the quantity of an item in their cart
  - User applies a coupon code
  - User removes an item from their cart
- __Checkout__
  - User tries to checkout with an empty cart
  - User completes checkout with products in the cart
- __Contact__
  - User sends an inquiry via the contact form
- __Newsletter__
  - User subscribes to the newsletter
  - User unsubscribes from the newsletter
- __Products__
  - User views a product's description
  - User checks the stock status of a product
  - User adds a product to their wishlist
  - User removes a product to their wishlist
- __Search by Keyword__
  - User searches for a specific product category
  - User searches for a specific product model
  - User searches using descriptive terms
  - User searches with terms that yield no results
- __Sitemap__
  - User checks if all categories are listed in the site map
  - User checks if links in the site map are functional
- __Special Offers__
  - User checks if a product is on sale
  - User adds a sale product to the shopping cart and checks discount
## Prerequisites
To run these tests, ensure you have the following installed:
- Java (JDK 8 or higher)
- Maven
- Google Chrome Browser
- chromedriver
## Installation
1. Clone the repository to your local machine:

```
git clone https://github.com/polpages1999/cucumber-testing.git
```
2. Navigate to the cloned repository directory:
```
cd cucumber-testing
```
3. Install the required dependencies via Maven:
```
mvn clean install
```
## Running the Tests
To run the entire test suite, execute the TestRunner class located in the _src/test/java/runner_ directory.
## Structure
The test suite is structured as follows:
- __Drivers/chromedriver.exe__: Contains the driver executable for Chrome.
- __src/test/java/runner__: Contains the test runner file with the Cucumber options.
- __src/test/java/features__: Contains the .feature files which describe the test scenarios in Gherkin language.
- __src/test/java/steps__: Contains the Java class "FeatureSteps" that defines the steps of the features described.
## Test Reports
After running the tests, you can find the test reports in the _target_ directory, in a file named __cucumber-reports.html__.
