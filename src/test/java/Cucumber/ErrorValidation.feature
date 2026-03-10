@tag
  Feature: Error Validation

    @ErrorValidation
    Scenario Outline: Error validation of login
      Given I landed on Ecommerce Page
      When Logged in with username <name> and password <password>
      Then "Incorrect email or password." message is displayed
      Examples:
        |name                   |password        |
        |fsd.cbd20@gmail.com    |User1234        |
        |fsd.cbd25@gmail.com    |User1234        |
