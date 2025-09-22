@ui @smoke
Feature: Login
  As a user
  I want to sign in
  So that I can see my account

  @happy
  Scenario: Valid login
    Given I am on the login page
    When I sign in with credentials: "tomsmith" and password: "SuperSecretPassword!"
    Then I should see a "You logged into a secure area!" message

  @sad
  Scenario: Invalid login
    Given I am on the login page
    When I sign in with credentials: "baduser" and password: "secret"
    Then I should see a "Your username is invalid!" message

  @mix
  Scenario Outline: Vaild and invalid login
    Given I am on the login page
    When I sign in with credentials: "<user>" and password: "<pass>"
    Then I should see a "<message>" message

    Examples:
      | user     | pass                 | message                        |
      | tomsmith | SuperSecretPassword! | You logged into a secure area! |
      | baduser  | secret               | Your username is invalid!      |