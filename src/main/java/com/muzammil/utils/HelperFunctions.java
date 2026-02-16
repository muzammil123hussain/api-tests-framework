package com.muzammil.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HelperFunctions {
    public JsonNode convertStrToJSON(String str) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(str);
    }

    public static String generateRandomFourDigitNumber() {
        Random random = new Random();
        int min = 1000;
        int max = 9999;
        int result = random.nextInt(max - min + 1) + min;
        return String.valueOf(result);
    }

    public static boolean compareResponseObjects(Object postObject, Object getObject) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            String postJson = mapper.writeValueAsString(postObject);
            String getJson = mapper.writeValueAsString(getObject);

            return postJson.equals(getJson);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<String> convertStringToList(String input) {
        String[] splitValues = input.split(",");
        return Arrays.asList(splitValues);
    }
}
