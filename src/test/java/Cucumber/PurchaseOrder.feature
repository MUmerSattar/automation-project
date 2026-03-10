@tag
  Feature: Purchase the order from Ecommerce Website

    Background:
      Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Testing of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Verify <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage


    Examples:
    |name                   |password        |productName     |
    |fsd.cbd13@gmail.com    |User1234        |ZARA COAT 3     |
    |fsd.cbd14@gmail.com    |User1234        |ADIDAS ORIGINAL |