package com.kms.api;

import com.kms.api.utils.Utils;
import java.io.File;
import java.io.IOException;

/** Hello world! */
public class App {
  public static void main(String[] args) throws IOException {
    Utils.convertJsonToJavaClass(
        new File("src/main/resources/data/laptop-bag.json").toURI().toURL(),
        new File("src/main/java/com/kms/api"),
        "model",
        "LaptopBag");
  }
}
