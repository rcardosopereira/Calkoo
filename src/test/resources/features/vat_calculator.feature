Feature: Vat Calculator
  Scenario: Validate selected country and VAT rate
    Given I am on the VAT Calculator page
    When I select a country "Portugal"
    And I select a VAT rate "23%"
    Then the selected country should be "Portugal"
    And the selected VAT rate should be "23%"

  Scenario: Verify that the user can choose a valid VAT rate for the selected country
    Given I am on the VAT Calculator page
    When I select a country "Germany"
    And I select other VAT rate "19%"
    Then the selected VAT rate should be displayed correctly in the calculator
    When I enter the Net amount "100"
    Then the calculated Gross amount should be "119.0"
    And the calculated VAT amount should be "19"

  Scenario Outline: Validate VAT calculations
    Given I am on the VAT Calculator page
    When I select a country "<country>"
    And I select a VAT rate "<vatRate>"
    And I enter the Net amount "<netAmount>"
    Then the calculated Gross amount should be "<grossAmount>"
    And the calculated VAT amount should be "<vatAmount>"

    Examples:
      | country           | vatRate | netAmount | grossAmount | vatAmount |
      | Germany           | 19%     | 100       | 119.0       | 19        |
      | Portugal          | 23%     | 200       | 246.0       | 46        |
      | France            | 20%     | 100.00    | 120.00      | 20.00     |
      | Germany           | 19%     | 50.00     | 59.50       | 9.50      |
      | United Kingdom    | 20%     | 25.50     | 30.60       | 5.10      |

  Scenario: Error Handling for Negative Input
    Given I am on the VAT Calculator page
    When I select a country "France"
    And I select other VAT rate "20%"
    When I enter the Net amount "-100"
    Then I should see an error message "Negative values are invalid for a pie chart.×"