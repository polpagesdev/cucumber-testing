Feature: Account

  Scenario: User registers a new account
    Given the user is on the "account/create" page
    When the user enters valid registration details
    When the user clicks the "Continue" button [with tag "button" and attribute "title"]
    Then the user should be redirected to the "account/success" page

  Scenario: User logs in with valid credentials
    Given the user is on the "account/login" page
    And the user inputs "thisisatestaccount" in "loginFrm_loginname" [with tag "input" and attribute "id"]
    And the user inputs "test" in "loginFrm_password" [with tag "input" and attribute "id"]
    When the user clicks the "Login" button [with tag "button" and attribute "title"]
    Then the user should be redirected to the "account/account" page

  Scenario: User logs out from the account
    Given the user is set up
    And the user is on the "account/account" page
    When the user clicks the "Logoff" button [with tag "a" and attribute "title"]
    Then the user should be redirected to the "account/logout" page
