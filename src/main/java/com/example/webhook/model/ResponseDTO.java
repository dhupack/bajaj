package com.example.webhook.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDTO {
    @JsonProperty("webhook")
    private String webhookUrl;

    @JsonProperty("accessToken")
    private String accessToken;

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
