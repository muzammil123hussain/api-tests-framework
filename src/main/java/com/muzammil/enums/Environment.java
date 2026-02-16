package com.muzammil.enums;

import com.muzammil.exception.ApiException;
import lombok.Getter;

@Getter
public enum Environment {
    QA("qa", "qa.properties");

    private final String name;
    private final String properties;

    Environment(String name, String properties) {
        this.name = name;
        this.properties = properties;
    }

    public static Environment changeToEnvironment(String value) throws ApiException {
        if (value.equalsIgnoreCase("qa") || value.equalsIgnoreCase(Environment.QA.getName())) {
            value = "QA";
        }
        try {
            return Environment.valueOf(value);
        } catch (IllegalArgumentException ex) {
            throw new ApiException("The following environment: " + value + " don't have associated an environment in AWS");
        }
    }
}
