Feature: Validate votes

  Scenario: Create Vote
    Given that the user set image_id as "asf2" and sub_id as "my-user-1234" and value as "1" to create the vote
    And user has an authentication key
    When the user does a POST request on "/votes"
    Then the API returns the vote_id
    And status code 200

  Scenario: test