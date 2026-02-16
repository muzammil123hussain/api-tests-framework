package com.muzammil.services.ApiServices;

import com.muzammil.dataproviders.ApiProperties;
import com.muzammil.enums.ApiEnum;
import com.muzammil.exception.ApiException;
import com.muzammil.mappers.AgencyMapper;
import com.muzammil.mappers.LoginMapper;
import com.muzammil.models.Login.LoginPostResponse;
import com.muzammil.models.Agency.GetSingleAgencyResponse;
import com.muzammil.models.Agency.PostAgencyResponse;
import com.muzammil.services.common.CommonServices;
import io.cucumber.datatable.DataTable;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIService {

    private static final String LOGIN_PATH = "/auth/login";
    private static final String ADD_AGENCY_PATH = "/agencies/add";
    private static final String GET_AGENCY_PATH = "/agencies/{ID}";

    private static final String ID = "ID";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    public Response iPostLoginData() throws ApiException {
        Map<String, String> requestHeaders = new HashMap<>();

        RequestSpecification request = given().filter(new AllureRestAssured())
                .baseUri(new ApiProperties().getMicroservices(ApiEnum.BASE_URL))
                .contentType(ContentType.JSON)
                .headers(requestHeaders)
                .body(new LoginMapper().postLoginMethod(
                        CommonServices.getParameters().get(EMAIL),
                        CommonServices.getParameters().get(PASSWORD)))
                .basePath(LOGIN_PATH);
        request.log().all();
        Response response = request
                .when()
                .post();
        response.then().log().all();
        return response;
    }

    public Response iPostAgencyDataWithoutAuth() throws ApiException {

        Map<String, String> requestHeaders = new HashMap<>();

        RequestSpecification request = given()
                .filter(new AllureRestAssured())
                .baseUri(new ApiProperties().getMicroservices(ApiEnum.BASE_URL))
                .contentType(ContentType.JSON)
                .headers(requestHeaders)
                .body(new AgencyMapper().postAgencyMethod(
                        CommonServices.getParameters().get(NAME),
                        CommonServices.getParameters().get(EMAIL),
                        CommonServices.getParameters().get(PHONE),
                        CommonServices.getParameters().get(ADDRESS)
                ))
                .basePath(ADD_AGENCY_PATH);

        request.log().all();

        Response response = request
                .when()
                .post();

        response.then().log().all();

        return response;
    }

    public void iCheckTheLoginResponse(Response response) {
        response.getBody().as(LoginPostResponse.class);
    }

    public void iSaveToken(Response response) {
        CommonServices.aParameter("accessToken", response.getBody().as(LoginPostResponse.class).getTokens().getAccess().getToken());
    }

    public Response iPostAgencyDataWithAuth() throws ApiException {

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", "Bearer " + CommonServices.getParameters().get("accessToken"));
        RequestSpecification request = given()
                .filter(new AllureRestAssured())
                .baseUri(new ApiProperties().getMicroservices(ApiEnum.BASE_URL))
                .contentType(ContentType.JSON)
                .headers(requestHeaders)
                .body(new AgencyMapper().postAgencyMethod(
                        CommonServices.getParameters().get(NAME),
                        CommonServices.getParameters().get(EMAIL),
                        CommonServices.getParameters().get(PHONE),
                        CommonServices.getParameters().get(ADDRESS)
                ))
                .basePath(ADD_AGENCY_PATH);

        request.log().all();

        Response response = request
                .when()
                .post();

        response.then().log().all();

        return response;
    }

    public void iCheckTheAddAgencyResponse(Response response) {
        response.getBody().as(PostAgencyResponse.class);
    }

    public void iSaveAgencyID(Response response) {
        CommonServices.aParameter("ID", String.valueOf(response.getBody().as(PostAgencyResponse.class).getAgency().getId()));
    }

    public Response iPostGetAgencyDataWithAuth() throws ApiException {

        Map<String, String> requestHeaders = new HashMap<>();

        requestHeaders.put("Authorization", "Bearer " + CommonServices.getParameters().get("accessToken"));

        RequestSpecification request = given()
                .filter(new AllureRestAssured())
                .baseUri(new ApiProperties().getMicroservices(ApiEnum.BASE_URL))
                .contentType(ContentType.JSON)
                .headers(requestHeaders)
                .pathParam(ID, CommonServices.getParameters().get(ID))
                .basePath(GET_AGENCY_PATH);

        request.log().all();

        Response response = request
                .when()
                .get();

        response.then().log().all();

        return response;
    }

    public void iCheckTheGetAgencyResponse(Response response) {
        response.getBody().as(GetSingleAgencyResponse.class);
    }

    public boolean validateAgencyResponse(Response response, DataTable dataTable) {
        GetSingleAgencyResponse agencyResponse = response.getBody().as(GetSingleAgencyResponse.class);

        Map<String, String> expectedValues = dataTable.asMap(String.class, String.class);

        GetSingleAgencyResponse.Agency actualAgency = agencyResponse.getResponse();

        return expectedValues.get("name").equals(actualAgency.getName()) &&
                expectedValues.get("address").equals(actualAgency.getAddress()) &&
                expectedValues.get("phone").equals(actualAgency.getPhone()) &&
                expectedValues.get("email").equals(actualAgency.getEmail());

    }

}