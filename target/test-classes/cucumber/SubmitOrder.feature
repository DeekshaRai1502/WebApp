
@tag
Feature: Purchase the order from Ecommerce Website


 Background: 
 Given I landed on Ecommerce page


  @Regression123
  Scenario Outline: Positive Test of Submitting the Order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation page

    Examples: 
      | name  							| password |			| productName |
      | admin@gmail.com 		| Password5 |			| ZARA JACKET |
      | anshika@gmail.com		| Iamking@000 |		| ADIDAS ORIGINAL |
