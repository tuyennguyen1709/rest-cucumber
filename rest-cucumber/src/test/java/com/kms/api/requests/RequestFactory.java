package com.kms.api.requests;

import com.kms.api.model.LaptopBag;
import com.kms.api.tests.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.logging.Logger;

public class RequestFactory extends TestBase {

  private static final Logger logger = Logger.getLogger(String.valueOf(RequestFactory.class));

  /**
   * Send request to create a new product
   *
   * @param requestPayload
   * @return Response of the request
   */
  public static Response addProduct(String path, LaptopBag requestPayload) {
    logger.info("Adding new product.");
    Response res = RestClient.doPostRequestWithPayload(path, ContentType.JSON, requestPayload);
    logger.info(String.format("The request for adding the product completed"));
    return res;
  }
}
