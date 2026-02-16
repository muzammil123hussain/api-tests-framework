package com.muzammil.stepdefinition.common;

import com.muzammil.cucumber.ScenarioContext;
import com.muzammil.exception.ApiException;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hook {

    public static ScenarioContext scenarioContext;


    @BeforeAll
    public static void beforeAll() throws ApiException {
    }

    @Before
    public static void before() {
        scenarioContext = new ScenarioContext();
    }

    @After
    public static void after() {

    }

    @AfterAll
    public static void afterAll() throws IOException, ApiException {

        String sourcePath = "target/classes/environment.properties";

        Properties properties = new Properties();

        Path path = Path.of(sourcePath);

        try (InputStream input = Files.newInputStream(path)) {
            properties.load(input);
        } catch (IOException e) {
            throw new ApiException(e.getMessage());
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(dateTimeFormatter);

        properties.setProperty("system_date_time", currentDateTime);
        properties.setProperty("os_platform", System.getProperty("os.name"));
        properties.setProperty("os_version", System.getProperty("os.version", "Unknown Version"));
        properties.setProperty("java_version", System.getProperty("java.version"));

        try {
            OutputStream output = Files.newOutputStream(path);
            properties.store(output, "Updated Environment Properties with Dynamic Values");
        } catch (IOException e) {
            throw new ApiException(e.getMessage());
        }

        String destinationPath = "target/allure-results/environment.properties";
        Files.copy(path, Path.of(destinationPath), StandardCopyOption.REPLACE_EXISTING);
    }
}

