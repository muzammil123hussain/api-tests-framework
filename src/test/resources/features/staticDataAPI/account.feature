@account-service
@staticDataAPIService
Feature: Account

  @crud
  Scenario Outline: CRUD Account - Valid call for 'CRUD Account'
    # Create
    Given I generate random number and save it
    And A 'booth' param with value: '<booth>'
    And A 'isBoxVsShort' param with value: '<isBoxVsShort>'
    And A 'mpId' param with value: '<mpId>'
    And A 'name' param with value: '<name>'
    And A 'value' param with value: '<value>' with postfix
    And A 'accountType' param with value: '<accountType>'
    And A 'isRiskEnabled' param with value: '<isRiskEnabled>'
    And A 'additonalInfo' param with value: '<additonalInfo>'
    And A 'seekInfo' param with value: '<seekInfo>'
    When I post 'create-account' data
    Then I check the response code is 200
    And I check the 'create-or-update' response is correct using '<modelsPackageName>' and save the response
    And I save 'created-resource' 'id' for further use from '<modelsPackageName>'

    # Read
    When I post 'get-data' using '<baseUrl>''<getUrl>' with '<getParams>'
    Then I check the response code is 200
    And I check the 'get-single' response is correct using '<modelsPackageName>' and save the response
    And I check the 'post' response is equals to 'get' response

    # Update
    And A 'name' param with value: '<new-name>' with postfix
    When I post 'update-account' data
    Then I check the response code is 200
    And I check the 'create-or-update' response is correct using '<modelsPackageName>' and save the response

    # Delete
    When I post 'delete' data using '<baseUrl>''<deleteUrl>' with '<deleteParam>'
    Then I check the response code is 200

    Examples:
      | booth | isBoxVsShort | mpId | name         | value         | new-name            | accountType | isRiskEnabled | additonalInfo | seekInfo | modelsPackageName | baseUrl         | deleteUrl      | deleteParam | getUrl                   | getParams |
      | TEST  | false        | ARE  | TESTMUZAMMIL | TESTMUZAMMIL- | TESTMUZAMMILUPDATED | trader      | false         | TEST          | 0        | account           | /api/v1/Account | /DeleteAccount | id          | /GetAccountsByValueAsync | value     |

  @create
  Scenario Outline: Create & Delete Account - Valid call for 'Create & Delete Account'
    # Create
    Given I generate random number and save it
    And A 'booth' param with value: '<booth>'
    And A 'isBoxVsShort' param with value: '<isBoxVsShort>'
    And A 'mpId' param with value: '<mpId>'
    And A 'name' param with value: '<name>'
    And A 'value' param with value: '<value>' with postfix
    And A 'accountType' param with value: '<accountType>'
    And A 'isRiskEnabled' param with value: '<isRiskEnabled>'
    And A 'additonalInfo' param with value: '<additonalInfo>'
    And A 'seekInfo' param with value: '<seekInfo>'
    When I post 'create-account' data
    Then I check the response code is 200
    And I check the 'create-or-update' response is correct using '<modelsPackageName>' and save the response
    And I save 'created-resource' 'id' for further use from '<modelsPackageName>'

    # Delete
    When I post 'delete' data using '<baseUrl>''<deleteUrl>' with '<deleteParam>'
    Then I check the response code is 200

    Examples:
      | booth | isBoxVsShort | mpId | name         | value         | accountType | isRiskEnabled | additonalInfo | seekInfo | baseUrl         | deleteUrl      | deleteParam | modelsPackageName |
      | TEST  | false        | ARE  | TESTMUZAMMIL | TESTMUZAMMIL- | trader      | false         | TEST          | 0        | /api/v1/Account | /DeleteAccount | id          | account           |

  @pagination
  Scenario Outline: Create, Get with Pagination & Delete Account - Valid call for 'Create, Get with Pagination & Delete Account'
    # Create
    Given I generate random number and save it
    And A 'booth' param with value: '<booth>'
    And A 'isBoxVsShort' param with value: '<isBoxVsShort>'
    And A 'mpId' param with value: '<mpId>'
    And A 'name' param with value: '<name>'
    And A 'value' param with value: '<value>' with postfix
    And A 'accountType' param with value: '<accountType>'
    And A 'isRiskEnabled' param with value: '<isRiskEnabled>'
    And A 'additonalInfo' param with value: '<additonalInfo>'
    And A 'seekInfo' param with value: '<seekInfo>'
    When I post 'create-account' data
    Then I check the response code is 200
    And I check the 'create-or-update' response is correct using '<modelsPackageName>' and save the response
    And I save 'created-resource' 'id' for further use from '<modelsPackageName>'
    Then I check the response code is 200

    # Read
    And A 'pageNumber' param with value: '<pageNumber>'
    And A 'pageSize' param with value: '<pageSize>'
    When I post 'get-data-with-pagination' using '<baseUrl>''<paginationUrl>'
    Then I check the response code is 200
    And I check the 'get-data-with-pagination' response is correct using '<modelsPackageName>'

    # Delete
    When I post 'delete' data using '<baseUrl>''<deleteUrl>' with '<deleteParam>'
    Then I check the response code is 200

    Examples:
      | booth | isBoxVsShort | mpId | name         | value         | accountType | isRiskEnabled | additonalInfo | seekInfo | baseUrl         | deleteUrl      | deleteParam | modelsPackageName | paginationUrl              | pageNumber | pageSize |
      | TEST  | false        | ARE  | TESTMUZAMMIL | TESTMUZAMMIL- | trader      | false         | TEST          | 0        | /api/v1/Account | /DeleteAccount | id          | account           | /GetAccountsWithPagination | 1          | 2        |

  @create @negative
  Scenario Outline: Create Account - Invalid call for 'Create Account'
    # Create
    Given A 'booth' param with value: '<booth>'
    And A 'isBoxVsShort' param with value: '<isBoxVsShort>'
    And A 'mpId' param with value: '<mpId>'
    And A 'name' param with value: '<name>'
    And A 'value' param with value: '<value>'
    And A 'accountType' param with value: '<accountType>'
    And A 'isRiskEnabled' param with value: '<isRiskEnabled>'
    And A 'additonalInfo' param with value: '<additonalInfo>'
    And A 'seekInfo' param with value: '<seekInfo>'
    When I post 'create-account' data
    Then I check the response code is 400
    And I check the 'error' response is correct and save the response
    And I verify that the '<error-parameters>' in response 'error-parameters'
    And I verify that the '<error-messages>' in response 'error-messages' with '<error-parameters>'

    Examples:
      | booth | isBoxVsShort | mpId | name         | value         | accountType | isRiskEnabled | additonalInfo | seekInfo | error-parameters | error-messages                                                                        |
      |       | false        | ARE  | TESTMUZAMMIL | TESTMUZAMMIL- | trader      | false         | TEST          | 0        | Booth            | The Booth field is required.                                                          |
      | TEST  | false        | ARE  |              | TESTMUZAMMIL- | trader      | false         | TEST          | 0        | Name             | The Name field is required.                                                           |
      | TEST  | false        | ARE  | TESTMUZAMMIL |               | trader      | false         | TEST          | 0        | Value            | The Value field is required.                                                          |
      |       | false        | ARE  |              |               | trader      | false         | TEST          | 0        | Booth,Name,Value | The Booth field is required.,The Name field is required.,The Value field is required. |

  @update @negative
  Scenario Outline: Update Account - Invalid call for 'Update Account'
    # Create
    Given I generate random number and save it
    And A 'booth' param with value: '<booth>'
    And A 'isBoxVsShort' param with value: '<isBoxVsShort>'
    And A 'mpId' param with value: '<mpId>'
    And A 'name' param with value: '<name>'
    And A 'value' param with value: '<value>' with postfix
    And A 'accountType' param with value: '<accountType>'
    And A 'isRiskEnabled' param with value: '<isRiskEnabled>'
    And A 'additonalInfo' param with value: '<additonalInfo>'
    And A 'seekInfo' param with value: '<seekInfo>'
    When I post 'create-account' data
    Then I check the response code is 200
    And I check the 'create-or-update' response is correct using '<modelsPackageName>' and save the response
    And I save 'created-resource' 'id' for further use from '<modelsPackageName>'

    # Update
    And A 'booth' param with value: '<new-booth>'
    And A 'name' param with value: '<new-name>'
    And A 'value' param with value: '<new-value>'
    When I post 'update-account' data
    Then I check the response code is 400
    And I check the 'error' response is correct and save the response
    And I verify that the '<error-parameters>' in response 'error-parameters'
    And I verify that the '<error-messages>' in response 'error-messages' with '<error-parameters>'

    # Delete
    When I post 'delete' data using '<baseUrl>''<deleteUrl>' with '<deleteParam>'
    Then I check the response code is 200

    Examples:
      | booth | new-booth | isBoxVsShort | mpId | name         | new-name     | value         | new-value     | accountType | isRiskEnabled | additonalInfo | seekInfo | error-parameters | error-messages                                                                        | modelsPackageName | baseUrl         | deleteUrl      | deleteParam |
      | TEST  |           | false        | ARE  | TESTMUZAMMIL | TESTMUZAMMIL | TESTMUZAMMIL- | TESTMUZAMMIL- | trader      | false         | TEST          | 0        | Booth            | The Booth field is required.                                                          | account           | /api/v1/Account | /DeleteAccount | id          |
      | TEST  | TEST      | false        | ARE  | TESTMUZAMMIL |              | TESTMUZAMMIL- | TESTMUZAMMIL- | trader      | false         | TEST          | 0        | Name             | The Name field is required.                                                           | account           | /api/v1/Account | /DeleteAccount | id          |
      | TEST  | TEST      | false        | ARE  | TESTMUZAMMIL | TESTMUZAMMIL | TESTMUZAMMIL- |               | trader      | false         | TEST          | 0        | Value            | The Value field is required.                                                          | account           | /api/v1/Account | /DeleteAccount | id          |
      | TEST  |           | false        | ARE  | TESTMUZAMMIL |              | TESTMUZAMMIL- |               | trader      | false         | TEST          | 0        | Booth,Name,Value | The Booth field is required.,The Name field is required.,The Value field is required. | account           | /api/v1/Account | /DeleteAccount | id          |
