@product @update
Feature: Update an existing product

  Background:
    Given the path "add" to the endpoint
    And the payload of the request with BrandName as "Dell", Features as "8GB RAM,1TB Hard Drive", LaptopName as "Latitude"
    When I perform the request to add new product

  Scenario Outline: <TC> - Post and then update the details
    Given the path "update" to the endpoint
    When I perform the PUT request with id and BrandName as "<brandName>", Features as "<feature>", LaptopName as "<laptopName>"
    Then the status code "200" should return
    And Details should get updated BrandName as "<brandName>", Features as "<feature>", LaptopName as "<laptopName>"
    Examples:
      | brandName | feature                 | laptopName | TC                     |
      | Dell      | 8GB RAM,1TB Hard Drive  | Latitude   | TC_01_newValue         |
      |           | 16GB RAM,1TB Hard Drive | Latitude   | TC_02_blankBranchName  |
      | MAC       |                         | Apple      | TC_03_blankFeature     |
      | MAC       | 32GB RAM, 1TB SSD       |            | TC_03_blankLaptopName  |
      |           |                         |            | TC_04_blankValue       |
      | #?*       | ....                    | ???///     | TC_05_specialCharacter |

  Scenario Outline: <TC> - Post and then update with invalid payload (json request body)
    Given the path "update" to the endpoint
    But I perform the PUT request with id and invalid json payload as "<invalidBody>"
    Then the status code "400" should return
    Examples:
      | invalidBody | TC                 |
      | null        | TC_01_nullValue    |
      |             | TC_02_blankValue   |
      | wrong body  | TC_03_invalidValue |