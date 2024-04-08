
@tag
Feature: Purchase Order From Ecommerce Website
  I want to use this template for my feature file
  
  Background:
	Given I landed on Ecommerce Page

  @tag2
  Scenario Outline: Positive Test of Submitting Order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the Order
    Then "THANKYOU FOT THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  						| 	password |	productName |
      |123456gk@gmail.com |		1234@Abcd	| ZARA COAT 3 |
