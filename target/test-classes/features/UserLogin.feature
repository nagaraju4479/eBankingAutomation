Feature: User login to eBanking application


  Background: Register new user to eBanking application
    Given I am on home page
    And I navigate to user login page as a User
    When I go to registration page
    And I register user using below info:
      | firstName    | user_${random}           |
      | lastName     | yajjuvarapu              |
      | emailAddress | user_${random}@gmail.com |
      | mobile       |               9999999999 |
      | password     | ${password}                 |
    Then I should see registration form sumbitted successfully
  	When I login to the applicaiton
  	Then I should successfully logged in to the application
  	
  @test @UserLogin
  Scenario: user login to eBanking applicaiton
    Given I am on home page
    When I navigate to user login page as a User
    And I login to application with valid credentials:
      | emailId  | user@gmail.com |
      | password | user@123       |
    Then I should successfully logged in to the application
  	
		