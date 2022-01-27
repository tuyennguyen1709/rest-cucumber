@product @add
Feature: Add new product

  Scenario Outline: <TC> - Add new a product
    Given the path "add" to the endpoint
    And the payload of the request with BrandName as "<brandName>", Features as "<feature>", LaptopName as "<laptopName>"
    When I perform the request to add new product
    Then the status code "200" should return
    And the product is added successfully with an integer Id
    Examples:
      | brandName | feature                 | laptopName | TC                     |
      | Dell      | 8GB RAM,1TB Hard Drive  | Latitude   | TC_01_fullValue        |
      |           | 16GB RAM,1TB Hard Drive | Latitude   | TC_02_blankBranchName  |
      | MAC       |                         | Apple      | TC_03_blankFeature     |
      | MAC       | 32GB RAM, 1TB SSD       |            | TC_03_blankLaptopName  |
      |           |                         |            | TC_04_blankValue       |
      | #?*       | ....                    | ???///     | TC_05_specialCharacter |

  Scenario Outline: <TC> - Add new product with invalid payload (json request body)
    Given the path "add" to the endpoint
    But I supply invalid json payload as "<invalidBody>"
    When I perform the request to add new product
    Then the status code "400" should return

    Examples:
      | invalidBody | TC                 |
      | null        | TC_01_nullValue    |
      |             | TC_02_blankValue   |
      | wrong body  | TC_03_invalidValue |
