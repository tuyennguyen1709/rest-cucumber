package com.kms.api.tests;

import static com.kms.api.util.RestUtil.mapRestResponseToPojo;

import com.kms.api.model.LaptopBag;
import com.kms.api.requests.RequestFactory;
import com.kms.api.util.RequestBuilder;
import com.kms.api.util.ValidationUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpStatus;

public class AddProductSteps extends TestBase {

  private String path = "";
  private LaptopBag reqLaptop;
  private LaptopBag resLaptop;
  private int id = (int) (Math.random() * 10000);
  private Response res;

  @Given("^the path \"([^\"]*)\" to the endpoint$")
  public void thePathToAddTheProduct(String path) {
    this.path = path;
  }

  @And(
      "^the payload of the request with BrandName as \"([^\"]*)\", Features as \"([^\"]*)\", LaptopName as \"([^\"]*)\"$")
  public void thePayloadOfTheRequestWithBrandNameAsFeaturesAsLaptopNameAs(
      String brandName, String feature, String laptopName) {
    String[] array = feature.split(",");
    List<String> lst = Arrays.asList(array);
    reqLaptop = RequestBuilder.requestPayload(laptopName, brandName, id, lst);
  }

  @When("^I perform the request to application$")
  public void iPerformTheRequestToApplication() {
    res = RequestFactory.addProduct(path, reqLaptop);
    resLaptop = mapRestResponseToPojo(res, LaptopBag.class);
  }

  @Then("^the status code \"([^\"]*)\" should return$")
  public void theStatusCodeShouldReturn(String arg0) {
    ValidationUtil.validateStatusCode(res, HttpStatus.SC_OK);
  }

  @And("^the product is added successfully with an integer Id$")
  public void theProductIsAddedSuccessfullyWithAnIntegerId() {
    ValidationUtil.validateStringEqual(resLaptop.getId(), id);
  }
}
