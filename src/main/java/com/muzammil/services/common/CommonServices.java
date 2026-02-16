package com.muzammil.services.common;

import com.muzammil.models.errorModel.ErrorModelPostResponse;
import io.restassured.response.Response;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommonServices {

    static Logger logger = Logger.getLogger(CommonServices.class.getName());
    @Getter
    private static final Map<String, String> parameters;

    static {
        parameters = new HashMap<>();
    }

    //Private constructor to hide the implicit public one
    private CommonServices() {
    }

    public static void iCheckTheResponseCode(Response response, int statusCode) {
        response.then().statusCode(statusCode);
    }

    public static boolean aValidParameter(String key, String value) {
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
            parameters.put(key, value);
            return true;
        }
        return false;
    }

    public static void aParameter(String key, String value) {
        parameters.put(key, value);
    }

    public static void iCheckTheErrorResponseAndSaveTheResponse(Response response) {
        response.getBody().as(ErrorModelPostResponse.class);
    }


    public static boolean iCheckTheErrorMessageInResponse(Response response, String message) {
        String error = response.getBody().as(ErrorModelPostResponse.class).getMessage();
        if (!Objects.equals(message, error)) {
            logger.log(Level.WARNING,
                    "This error message is different from Response, Expected : " + message + "Actual : " + error);
            return false;
        }
        return true;
    }
}
