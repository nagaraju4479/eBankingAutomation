Feature: Manage Payee

  Scenario: Add Payee
    Given I am on Add Payee page
    When I fill below Add Payee details:
      | Account Number        | ${AccountNumber}        |
      | ConfirmAccount Number | ${ConfirmAccountNumber} |
      | Account Holder Name   | ${AccountHolderName}    |
    Then I should see Payee is added successfully

  Scenario: Delete Payee
    Given I am on Add Payee page
    When I go to Manage Payee page
    And Search an Payee account 'Account naumber'
    Then I should see Payee account is visible
    When I delect the Payee
    Then I should see Payee deleted successfully

  Scenario: Transfer money to Payee
    Given I am on Add Payee page
    When I go to Manage Payee page
    And Search an Payee account 'Account naumber'
    Then I should see Payee account is visible
    When I click 'transfer' button
    Then I should be navigated to Transfer Amount page
    When I transfer amount to Payee
    Then I should see amount transfered successfully
    When I go to user Dashboard page
    Then I should see balance updated successfully
    When I got to Transaction history page
    And I should see Transaction history updated successfully
		
	
		