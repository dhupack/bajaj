package com.example.webhook;

import com.example.webhook.service.WebhookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class WebhookApplication {

    private final WebhookService webhookService;

    public WebhookApplication(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebhookApplication.class, args);
    }

    @PostConstruct
    public void init() {
        webhookService.processWebhookFlow();
    }
}
