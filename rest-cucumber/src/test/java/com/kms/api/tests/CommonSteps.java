package com.kms.api.tests;

import com.kms.api.util.ValidationUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CommonSteps extends TestBase {


    @Given("^the path \"([^\"]*)\" to the endpoint$")
    public void thePathToAddTheProduct(String path) {
        this.path = path;
    }

    @Then("^the status code \"([^\"]*)\" should return$")
    public void theStatusCodeShouldReturn(String statusCode) {
        ValidationUtil.validateStatusCode(res, Integer.parseInt(statusCode));
    }
}
