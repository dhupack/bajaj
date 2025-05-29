package com.example.webhook.service;

import com.example.webhook.model.RequestDTO;
import com.example.webhook.model.ResponseDTO;
import com.example.webhook.util.SQLQueryProvider;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();

    public void processWebhookFlow() {
        // Step 1: POST to generate webhook
        RequestDTO request = new RequestDTO("Deepak Kumar", "22UCC032", "deepak@example.com");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RequestDTO> entity = new HttpEntity<>(request, headers);

        String generateUrl = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
        ResponseEntity<ResponseDTO> response = restTemplate.exchange(generateUrl, HttpMethod.POST, entity, ResponseDTO.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            ResponseDTO body = response.getBody();
            String accessToken = body.getAccessToken();
            String webhookUrl = body.getWebhookUrl();

            // Step 2: Choose query based on regNo (even/odd)
            String finalSQLQuery = SQLQueryProvider.getQuery(request.getRegNo());

            // Step 3: Submit the final SQL query
            HttpHeaders postHeaders = new HttpHeaders();
            postHeaders.setContentType(MediaType.APPLICATION_JSON);
            postHeaders.setBearerAuth(accessToken);

            String payload = String.format("{\"finalQuery\": \"%s\"}", finalSQLQuery.replace("\"", "\\\""));
            HttpEntity<String> postEntity = new HttpEntity<>(payload, postHeaders);

            restTemplate.exchange(webhookUrl, HttpMethod.POST, postEntity, String.class);
        }
    }
}
