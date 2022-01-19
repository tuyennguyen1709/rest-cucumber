package com.kms.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class LaptopBag {

  @JsonProperty("BrandName")
  private String BrandName;

  @JsonProperty("Features")
  private Features Features;

  @JsonProperty("Id")
  private Integer Id;

  @JsonProperty("LaptopName")
  private String LaptopName;

  public String getBrandName() {
    return BrandName;
  }

  public void setBrandName(String BrandName) {
    this.BrandName = BrandName;
  }

  public Features getFeatures() {
    return Features;
  }

  public void setFeatures(Features Features) {
    this.Features = Features;
  }

  public Integer getId() {
    return Id;
  }

  public void setId(Integer Id) {
    this.Id = Id;
  }

  public String getLaptopName() {
    return LaptopName;
  }

  public void setLaptopName(String LaptopName) {
    this.LaptopName = LaptopName;
  }
}
