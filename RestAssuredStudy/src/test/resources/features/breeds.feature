Feature: Validate breeds

  @Regression
  Scenario: List the Cat Breeds
    Given that there is a list of breed of cats
    And user has an authentication key
    When the user does a GET request on "/breeds"
    Then the API returns the list of cat breeds
    And status code 200

  @Smoke
  Scenario: Search Cat Breeds by name
    Given that there is a list of breed of cats
    And user has an authentication key
    And the user wants to search a breed by name
    When the user does a GET request on "/breeds/search"
    Then the API returns the list of cat breeds
    And status code 200