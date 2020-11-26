Feature: AMAZON e2e demo tests

  Scenario: Tests search results keyword
    Given the user opens the AMAZON application
    When the user searches with filter laptop
    Then search results are based with filter laptop


  Scenario: Tests search results keyword (in purpose fail)
    Given the user opens the AMAZON application
    When the user searches with filter laptop
    Then search results are based with filter laptop1