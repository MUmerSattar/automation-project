@tag
  Feature: Purchase the order from Ecommerce Website
    I want to use this template for my feature file


    Background:
      Given I landed on Ecommerce Page

  @tag2
  Scenario Outline: Positive Testing of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Verify <productName> and submit the order
    Then <ConfirmationMessage> message is displayed on ConfirmationPage


    Examples:
    |name                   |password        |productName     |ConfirmationMessage    |
    |fsd.cbd13@gmail.com    |User1234        |ZARA COAT 3     |THANKYOU FOR THE ORDER |
    |fsd.cbd14@gmail.com    |User1234        |ADIDAS ORIGINAL |THANKYOU FOR THE ORDER |