package com.muzammil.stepdefinition.common;

import com.muzammil.enums.Context;
import com.muzammil.services.common.CommonServices;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static com.muzammil.stepdefinition.common.Hook.scenarioContext;

public class CommonServiceSteps {

    @Given("A valid {string} param with value: {string}")
    public void aValidInputParam(String key, String value) {
        Assertions.assertTrue(CommonServices.aValidParameter(key, value));
    }

    @Given("A {string} param with value: {string}")
    public void aInputParam(String key, String value) {
        CommonServices.aParameter(key, value);
    }

    @And("I check the response code is {int}")
    public void iCheckTheResponseCode(int responseCode) {
        CommonServices.iCheckTheResponseCode((Response) scenarioContext.getContext(Context.RESPONSE), responseCode);
    }

    @And("I wait for {int} ms")
    public void iWaitForSeconds(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    @And("I check the 'error' response is correct and save the response")
    public void iCheckTheErrorResponseAndSaveTheResponse() {
        CommonServices.iCheckTheErrorResponseAndSaveTheResponse((Response) scenarioContext.getContext(Context.RESPONSE));
    }

    @And("I verify that the error-message {string} in response")
    public void iCheckTheErrorMessageInResponse(String messages) {
        Assertions.assertTrue(CommonServices.iCheckTheErrorMessageInResponse((Response) scenarioContext.getContext(Context.RESPONSE), messages));
    }
}
