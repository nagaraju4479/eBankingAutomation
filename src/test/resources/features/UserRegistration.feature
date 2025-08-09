Feature: User login to eBanking application

  
	@RegisterUser
  Scenario: Register new user to eBanking application
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
  	