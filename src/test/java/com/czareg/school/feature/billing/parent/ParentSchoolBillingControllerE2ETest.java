package com.czareg.school.feature.billing.parent;

import com.czareg.school.util.FileUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParentSchoolBillingControllerE2ETest {

    private static final String BASE_URL = "/billing/parent/school";
    public static final String RESPONSE_200_JSON = "parent_school_billing_200_response.json";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnProperBillingForGivenParentSchoolAndMonth() throws IOException {
        String requestJson = "{\"parentId\":1,\"schoolId\":1,\"month\":2}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(BASE_URL, HttpMethod.POST, request, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        JsonNode expectedResponse = objectMapper.readTree(FileUtils.readFileAsString(RESPONSE_200_JSON));
        JsonNode actualResponse = objectMapper.readTree(responseEntity.getBody());
        assertEquals(expectedResponse, actualResponse);
    }
}