package com.kms.api.tests;

import com.kms.api.model.LaptopBag;
import com.kms.api.requests.RequestFactory;
import com.kms.api.util.RequestBuilder;
import com.kms.api.util.ValidationUtil;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

import static com.kms.api.util.RestUtil.mapRestResponseToPojo;

public class UpdateProductSteps extends TestBase {

  private Object requestPayload;
  private LaptopBag reqPutLaptop;
  private LaptopBag resPutLaptop;

  @When("^I perform the PUT request with id and BrandName as \"([^\"]*)\", Features as \"([^\"]*)\", LaptopName as \"([^\"]*)\"$")
  public void iPerformThePUTRequestWithIdAndBrandNameAsFeaturesAsLaptopNameAs(String brandName, String feature, String laptopName) {
      String[] array = feature.split(",");
      List<String> lst = Arrays.asList(array);
      requestPayload = RequestBuilder.requestPayload(laptopName, brandName, id, lst);
      reqPutLaptop = (LaptopBag) requestPayload;
      res = RequestFactory.updateProduct(path, reqPutLaptop);
      resPutLaptop = mapRestResponseToPojo(res, LaptopBag.class);
  }

  @And("^Details should get updated BrandName as \"([^\"]*)\", Features as \"([^\"]*)\", LaptopName as \"([^\"]*)\"$")
  public void detailsShouldGetUpdated(String brandName, String feature, String laptopName) {
      ValidationUtil.validateStringEqual(resPutLaptop.getBrandName(),brandName);
      ValidationUtil.validateStringEqual(resPutLaptop.getLaptopName(),laptopName);
      String[] array = feature.split(",");
      List<String> lst = Arrays.asList(array);
      ValidationUtil.validateStringEqual(resPutLaptop.getFeatures().getFeature(),lst);
  }

    @But("^I perform the PUT request with id and invalid json payload as \"([^\"]*)\"$")
    public void iPerformThePUTRequestWithIdAndInvalidJsonPayloadAs(String invalidBody) {
        requestPayload = invalidBody;
        try {reqPutLaptop = (LaptopBag) requestPayload;
            res = RequestFactory.updateProduct(path, reqPutLaptop);
            resPutLaptop = mapRestResponseToPojo(res, LaptopBag.class);
        } catch (Exception ex){
            res = RequestFactory.updateProduct(path, requestPayload);
        }
    }
}
