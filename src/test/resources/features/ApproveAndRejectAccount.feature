Feature: Approve or Reject User account opening request
  As a Admin I can approve or reject the user opening request

  Background:
    Given I am on home page
    And I navigate to user login page as a Admin
    And I login to application with valid credentials:
      | emailId  | admin@gmail.com |
      | password | admin@123        |
    And I should successfully logged in to the application

  Scenario: Approve User Account
    When I click on 'New Account Request' link
    Then I should navigated to New Account Request page
    When I search an user account
    And I view the user account
    Then I should navigated to Account Holder Details page
    When I click on 'Take Action' button
    Then I should see 'Take Action' model window popup opened
    When I add below remarks:
      | Remarks        | Approved |
      | Status         | Approved |
      | Initial Amount | 10000    |
    Then I should see User account has been approved successfully
    When I search the user account and verify the account status
    Then I should see User account status is "Approved"





