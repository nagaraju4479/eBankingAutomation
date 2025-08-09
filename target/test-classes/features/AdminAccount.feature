Feature: Admin login to eBanking application

 @AdminLogin
  Scenario: Admin login to eBanking applicaiton
    Given I am on home page
    When I navigate to user login page as a Admin
   And I login to application with valid credentials:
     | emailId  | admin@gmail.com |
     | password | admin@123        |
   Then I should successfully logged in to the application