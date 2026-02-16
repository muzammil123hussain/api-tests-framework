package com.muzammil.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muzammil.models.Agency.PostAgencyRequest;

import java.util.Map;

public class AgencyMapper {
    public Map<String, Object> postAgencyMethod(String name, String email, String phone, String address) {
        PostAgencyRequest postAgencyRequest = new PostAgencyRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        postAgencyRequest.setName(name);
        postAgencyRequest.setEmail(email);
        postAgencyRequest.setPhone(phone);
        postAgencyRequest.setAddress(address);
        return objectMapper.convertValue(postAgencyRequest, Map.class);
    }
}