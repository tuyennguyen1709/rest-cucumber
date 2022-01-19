package com.kms.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import groovy.util.logging.Slf4j;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * RestUtil class containing util methods for validating, converting data, building requests.
 *
 * @author vihoang Date 25-Jul-2021
 */
@Slf4j
public class RestUtil {

  private static final String HYPHEN = "-";
  private static final String DATE_FORMAT = "yyyy-MM-dd";
  private static final String UTC = "UTC";

  /**
   * Building headers of a http request
   *
   * @param map map data of headers
   * @return HashMap<String, String> return headers under a hashmap
   */
  public static HashMap<String, String> addHeaders(Map map) {
    return new HashMap<String, String>(map);
  }

  /**
   * Building headers of a http request
   *
   * @param map params which is a part for building the endpoint
   * @return HashMap<String, String> return headers under a hashmap
   */
  public static HashMap<String, String> addParams(Map map) {
    return new HashMap<String, String>(map);
  }

  /**
   * Mapping an http body response to a pojo class
   *
   * @param res Http response is returned after sending a certain request to specific service
   * @param contentClass The data type which is used for mapping res to
   * @param <T> Generic type of class
   * @return instance of T - POJO
   */
  public static <T> T mapRestResponseToPojo(Response res, Class<T> contentClass) {
    return res.thenReturn().as(contentClass);
  }

  /**
   * Mapping a content json file to a pojo class
   *
   * @param location The folder holds data test files
   * @param fileName Name of the json file
   * @param aClass The current class is pointed
   * @param contentClass The data type which is used for mapping res to
   * @param <T> Generic type of class
   * @return instance of T - POJO
   */
  public static <T> T mapJsonFileToPojo(
      String location, String fileName, Class<?> aClass, Class<T> contentClass) {
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    InputStream is = null;
    try {
      is = aClass.getResource(location.concat(fileName)).openStream();
      return objectMapper.readValue(is, contentClass);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static <T> T mapJsonStringToPojo(String json, Class<T> contentClass) {
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    try {
      return objectMapper.readValue(json, contentClass);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Convert a content json file to string data
   *
   * @param location The folder holds data test files
   * @param fileName Name of the json file
   * @param aClass The current class is pointed
   * @return string data
   */
  public static String mapJsonFileToString(String location, String fileName, Class<?> aClass) {
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    InputStream is = null;
    String content = "";
    try {
      is = aClass.getResource(location.concat(fileName)).openStream();
      content = objectMapper.writeValueAsString(new ObjectMapper().readValue(is, Map.class));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return content;
  }

  /**
   * Convert a pojo instance to string data
   *
   * @param object An object of T data type
   * @param <T> Generic type of class
   * @return String data of the T object
   */
  public static <T> String mapPojoToString(T object) {
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    String content = "";
    try {
      content = objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return content;
  }

  /**
   * Getting value of a node which belongs to a Json content
   *
   * @param jsonContent content json as a string
   * @param objectName Name of the node in Json content
   * @return String Value of the specific node
   */
  public static String getValueOfNodeFromJsonContent(String jsonContent, String objectName) {
    return new JsonPath(jsonContent).getString(objectName);
  }

  /**
   * Remove hyphen from string date
   *
   * @param Date with format yyyy-MM-dd
   */
  public static String removeHyphenInDateString(String Date) {
    String[] temp = Date.split(HYPHEN);
    String expectedDate = "";
    for (String i : temp) {
      expectedDate += i;
    }
    return expectedDate;
  }

  /** Get current date with UTC and format yyyy-MM-dd */
  public static String getCurrentDateWithUTC() {
    SimpleDateFormat f = new SimpleDateFormat(DATE_FORMAT);
    f.setTimeZone(TimeZone.getTimeZone(UTC));
    return f.format(new Date());
  }

  /** Rounding a double number */
  public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
  }

  /** Convert string to date with format yyyy-mm-dd */
  public static Date convertStringToDateTime(String date, String format) throws ParseException {
    return new SimpleDateFormat(format).parse(date);
  }
}
