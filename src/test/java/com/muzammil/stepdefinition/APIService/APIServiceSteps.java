package com.muzammil.stepdefinition.APIService;

import com.muzammil.enums.Context;
import com.muzammil.exception.ApiException;
import com.muzammil.services.ApiServices.APIService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static com.muzammil.stepdefinition.common.Hook.scenarioContext;


public class APIServiceSteps {
    private final APIService authService;

    public APIServiceSteps() {
        this.authService = new APIService();
    }

    @When("I post 'login' data")
    public void iPostCreateAccountServiceData() throws ApiException {
        Response response = authService.iPostLoginData();
        scenarioContext.setContext(Context.RESPONSE, response);
    }

    @When("I post 'add-agency' data")
    public void iPostAgencyDataWithoutAuth() throws ApiException {
        Response response = authService.iPostAgencyDataWithoutAuth();
        scenarioContext.setContext(Context.RESPONSE, response);
    }

    @When("I post 'add-agency' data with token")
    public void iPostAgencyDataWithAuth() throws ApiException {
        Response response = authService.iPostAgencyDataWithAuth();
        scenarioContext.setContext(Context.RESPONSE, response);
    }

    @And("I check the 'login' response is correct")
    public void iCheckTheLoginResponse() {
        authService.iCheckTheLoginResponse((Response) scenarioContext.getContext(Context.RESPONSE));
    }

    @Then("I save access token for further use")
    public void iSaveToken() {
        authService.iSaveToken((Response) scenarioContext.getContext(Context.RESPONSE));
    }

    @And("I check the 'add-agency' response is correct")
    public void iCheckTheAddAgencyResponse() {
        authService.iCheckTheAddAgencyResponse((Response) scenarioContext.getContext(Context.RESPONSE));
    }

    @Then("I save token ID for further use")
    public void iSaveID() {
        authService.iSaveAgencyID((Response) scenarioContext.getContext(Context.RESPONSE));
    }


    @When("I post 'get-agency' data with token")
    public void iPostGetAgencyDataWithAuth() throws ApiException {
        Response response = authService.iPostGetAgencyDataWithAuth();
        scenarioContext.setContext(Context.RESPONSE, response);
    }

    @And("I check the 'get-agency' response is correct")
    public void iCheckTheGetAgencyResponse() {
        authService.iCheckTheGetAgencyResponse((Response) scenarioContext.getContext(Context.RESPONSE));
    }

    @And("the response should contain the following agency details:")
    public void theResponseShouldContainTheFollowingAgencyDetails(DataTable dataTable) {
        Response response = (Response) scenarioContext.getContext(Context.RESPONSE);
        Assertions.assertTrue(authService.validateAgencyResponse(response, dataTable));
    }

}
