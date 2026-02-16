@authenticationAndCrud-service
Feature: API Authentication and CRUD Operations Testing

  @Case1
  Scenario: Verify login with invalid credentials returns error message
    Given A 'email' param with value: 'admin@gmail.com'
    And A 'password' param with value: '123456'
    When I post 'login' data
    Then I check the response code is 500
    And I check the 'error' response is correct and save the response
    And I verify that the error-message 'Invalid credentials' in response

  @Case2
  Scenario: Verify create agency without authentication returns error
    Given A 'name' param with value: 'Muzammil'
    And A 'email' param with value: 'muzammilchuhan.lb@gmail.com'
    And A 'phone' param with value: '1234567890'
    And A 'address' param with value: 'some value'
    And I post 'add-agency' data
    Then I check the response code is 500
    And I check the 'error' response is correct and save the response
    And I verify that the error-message 'Please authenticate' in response

  @Case3
  Scenario: Verify authenticated user can create agency and retrieve it by ID
    Given A 'email' param with value: 'admin@gmail.com'
    And A 'password' param with value: 'admin@123'
    When I post 'login' data
    Then I check the response code is 200
    And I check the 'login' response is correct
    And I save access token for further use
    Given A 'name' param with value: 'Muzammil'
    And A 'email' param with value: 'muzammil21@gmail.com'
    And A 'phone' param with value: '1234567890'
    And A 'address' param with value: 'some value'
    And I post 'add-agency' data with token
    Then I check the response code is 200
    And I check the 'add-agency' response is correct
    And I save token ID for further use
    And I post 'get-agency' data with token
    And I check the 'get-agency' response is correct
    And the response should contain the following agency details:
      | name    | Muzammil             |
      | address | some value           |
      | phone   | 1234567890           |
      | email   | muzammil1@gmail.com |
