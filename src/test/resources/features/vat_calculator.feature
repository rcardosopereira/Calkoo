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