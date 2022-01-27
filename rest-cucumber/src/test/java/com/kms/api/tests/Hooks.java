package com.kms.api.tests;

import com.kms.api.requests.RequestFactory;
import io.cucumber.java.After;

public class Hooks extends TestBase{
    @After
    public void teardown(){
        res = RequestFactory.deleteProduct("/delete/"+id);
    }
}
