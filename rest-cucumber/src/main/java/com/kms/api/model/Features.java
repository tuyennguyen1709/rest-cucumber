package com.kms.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "Features")
public class Features {

  @JsonProperty("Feature")
  private List<String> Feature = new ArrayList<String>();

  public List<String> getFeature() {
    return Feature;
  }

  public void setFeature(List<String> Feature) {
    this.Feature = Feature;
  }
}
