package com.kms.api.tests;

import static io.restassured.RestAssured.*;

import com.kms.api.util.PropertyReader;
import groovy.util.logging.Slf4j;
import io.restassured.response.Response;

@Slf4j
public class TestBase {
  protected static int id = (int) (Math.random() * 10000);
  protected static String path = "";
  protected static Response res;
  protected static PropertyReader prop;

  public TestBase() {
    prop = PropertyReader.getInstance();
    baseURI = prop.getProperty("baseURI");
    port = Integer.parseInt(prop.getProperty("port"));
    basePath = prop.getProperty("basePath");
    authentication = preemptive().basic(prop.getProperty("username"), prop.getProperty("password"));
  }
}
