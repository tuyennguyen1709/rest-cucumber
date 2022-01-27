package com.kms.api.tests;

import static com.kms.api.util.RestUtil.mapRestResponseToPojo;

import com.kms.api.model.LaptopBag;
import com.kms.api.requests.RequestFactory;
import com.kms.api.util.RequestBuilder;
import com.kms.api.util.ValidationUtil;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;

public class AddProductSteps extends TestBase {

  private Object requestPayload;
  private LaptopBag reqAddLaptop;
  private LaptopBag resAddLaptop;

  @And(
      "^the payload of the request with BrandName as \"([^\"]*)\", Features as \"([^\"]*)\", LaptopName as \"([^\"]*)\"$")
  public void thePayloadOfTheRequestWithBrandNameAsFeaturesAsLaptopNameAs(
      String brandName, String feature, String laptopName) {
    String[] array = feature.split(",");
    List<String> lst = Arrays.asList(array);
    requestPayload = RequestBuilder.requestPayload(laptopName, brandName, id, lst);
  }

  @When("^I perform the request to add new product$")
  public void iPerformTheRequestToApplication() {
    try {reqAddLaptop = (LaptopBag) requestPayload;
      res = RequestFactory.addProduct(path, reqAddLaptop);
      resAddLaptop = mapRestResponseToPojo(res, LaptopBag.class);
    } catch (Exception ex){
      res = RequestFactory.addProduct(path, requestPayload);
    }
  }

  @And("^the product is added successfully with an integer Id$")
  public void theProductIsAddedSuccessfullyWithAnIntegerId() {
    ValidationUtil.validateStringEqual(resAddLaptop.getId(), id);
  }

  @But("^I supply invalid json payload as \"([^\"]*)\"$")
  public void iSupplyInvalidJsonPayload(String invalidBody) {
    requestPayload = invalidBody;
  }

}
