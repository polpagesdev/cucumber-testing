Feature: Contact

  Scenario: User sends an inquiry via the contact form
    Given the user is set up
    And the user is on the "content/contact" page
    When the user inputs "testname" in "first_name" [with tag "input" and attribute "name"]
    And the user inputs "testemail@test.com" in "email" [with tag "input" and attribute "name"]
    And the user inputs "enquiry details" in "enquiry" [with tag "textarea" and attribute "name"]
    When the user clicks the "Submit" button [with tag "button" and attribute "title"]
    Then the user should be redirected to the "content/contact/success" page
