package com.fabrick.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("connect")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FabrickProperties {
    private String baseUrl;
    private String authSchema;
    private String apiKey;
    private String accountId;
    private String balancePath;
    private String transactionsPath;
    private String transferPath;
}
