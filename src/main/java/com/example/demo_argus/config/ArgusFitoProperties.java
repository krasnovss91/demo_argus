package com.example.demo_argus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties("argusfito")
public class ArgusFitoProperties {

    private String username = "argusfito";

    private String password = "argusfito";

    private String baseUrl;
}
