package com.example.demo.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app")
public record Configuration(@NotNull Api api) {
    public record Api(@NotNull Client client) {
        public record Client(@Positive int retry, @Positive long timeout, @NotNull SendGrid sendgrid) {
            public record SendGrid(@NotBlank String key){}
        }
    }
}
