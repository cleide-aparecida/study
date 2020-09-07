Feature: Add more information to the user using page object

  Scenario Outline: Add more information to the user
    Given the user opens the browser
    And the user enter "login" and "password"
    When you click on the More About You tab
    And add desired "<type>" and "<contact>"
    Then a message should be displayed
    And we should check if the message is "Your contact has been added!"
    And the browser must be closed

    Examples:
    |type|contact|
    |Phone|+5588963256123|
    |Phone|+18896325444|
    |E-mail|cleideprado05gmailcom|