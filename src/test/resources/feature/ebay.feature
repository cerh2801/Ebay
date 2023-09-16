Feature: Searching for Products on eBay

  Background:
    Given the eBay website is open

  Scenario: Searching for a product
    When I enter the search keyword
    And I submit the search
    Then I should see the search results
    And order price descendent
    * show five first product in precy
    * Order and  print the products by name (ascendente)
