package com.muzammil.dataproviders;

import com.muzammil.enums.ApiEnum;
import com.muzammil.enums.Environment;
import com.muzammil.exception.ApiException;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

public class ApiProperties {

    private static final Properties envProperties = new Properties();
    private final Properties properties = new Properties();
    @Getter
    private final Environment environment;
    private static final String HTTPS = "https://";
    private static final String HTTP = "http://";

    static {
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(ApiProperties.class.getClassLoader().getResourceAsStream("execution_environment.properties"))));
        try {
            envProperties.load(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ApiProperties() throws ApiException {
        environment = Environment.changeToEnvironment(envProperties.getProperty("env.compilation"));
    }

    public ApiProperties(Environment environment) {
        this.environment = environment;
    }

    private void loadProperties() throws ApiException {
        BufferedReader reader;
        String propertiesFileName;
        if (environment.equals(Environment.QA)) {
            propertiesFileName = environment.getProperties();
        } else {
            throw new ApiException("There is something wrong, please check it");
        }
        reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(ApiProperties.class.getClassLoader().getResourceAsStream(propertiesFileName))));
        try {
            properties.load(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMicroservices(ApiEnum micro) throws ApiException {
        return getMicroservices(micro, false);
    }

    public String getMicroservices(ApiEnum micro, boolean isHttps) throws ApiException {
        loadProperties();
        String url = switch (micro) {
            case BASE_URL -> properties.getProperty("BaseUrl");
        };
        if (url != null && isHttps) {
            return HTTPS + url;
        } else if (url != null) {
            return HTTP + url;
        } else {
            throw new ApiException("url not specified in the .properties file");
        }
    }


}
