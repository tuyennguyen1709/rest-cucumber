package com.kms.api.util;

import com.kms.api.model.Features;
import com.kms.api.model.LaptopBag;
import java.util.List;

public class RequestBuilder {

  public static LaptopBag requestPayload(String name, String brand, int id, List<String> fea) {
    LaptopBag laptopBag = new LaptopBag();
    laptopBag.setLaptopName(name);
    laptopBag.setBrandName(brand);
    laptopBag.setId(id);
    Features features = new Features();
    features.setFeature(fea);
    laptopBag.setFeatures(features);
    return laptopBag;
  }
}
