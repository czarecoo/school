package com.czareg.school.feature.billing.school;

import com.czareg.school.config.ErrorResponse;
import com.czareg.school.util.FileUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolBillingControllerE2ETest {

    private static final String BASE_URL = "/billing/school";
    public static final String RESPONSE_200_JSON = "school_billing_200_response.json";
    public static final String RESPONSE_400_JSON = "school_billing_400_response.json";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnProperBillingForGivenSchoolAndMonth() throws IOException {
        String requestJson = "{\"schoolId\":2,\"month\":2}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(BASE_URL, HttpMethod.POST, request, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        JsonNode expectedResponse = objectMapper.readTree(FileUtils.readFileAsString(RESPONSE_200_JSON));
        JsonNode actualResponse = objectMapper.readTree(responseEntity.getBody());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void shouldReturnErrorResponseForIncorrectSchoolIdAndMonth() throws IOException {
        String requestJson = "{\"schoolId\":0,\"month\":0}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(BASE_URL, HttpMethod.POST, request, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ErrorResponse expectedResponse = objectMapper.readValue(FileUtils.readFileAsString(RESPONSE_400_JSON), ErrorResponse.class);
        ErrorResponse actualResponse = objectMapper.readValue(responseEntity.getBody(), ErrorResponse.class);
        assertEquals(expectedResponse.status(), actualResponse.status());
        assertEquals(expectedResponse.reason(), actualResponse.reason());
        assertEquals(expectedResponse.path(), actualResponse.path());
        assertThat(expectedResponse.errors()).containsExactlyInAnyOrderElementsOf(actualResponse.errors());
        assertEquals(expectedResponse.message(), actualResponse.message());
    }
}