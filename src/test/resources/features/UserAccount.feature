Feature: User Account

  Background: 
    Given I am on home page
    And I navigate to user login page as a User
    And I go to registration page
    And I register user using below info:
      | firstName    | user_${random}           |
      | lastName     | yajjuvarapu              |
      | emailAddress | user_${random}@gmail.com |
      | mobile       |               9000086190 |
      | password     | user@123                 |
    And I should see registration form sumbitted successfully

  @UserAccount @happy-path
  Scenario: Send New account opening request to Admin
    And I navigate to Home page
    When I navigate to user login page as a User
    And I login with valid credentials
    Then I should successfully logged in to the application
    When I go to Account opening page
    And I fill the account details with below deatils:
      | Address proof ID Number | ${aadharCard} |
      | PAN Card Number         | ${panNumber}  |
      | Address                 | Test Address  |
      | Date of Birth           |      08011987 |
    Then I should see success message as Details succesfully submitted.
