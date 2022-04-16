package com.example.demo_argus.config;

import com.example.demo_argus.handler.ArgusFitoResponseErrorHandler;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(ArgusFitoProperties.class)
@RequiredArgsConstructor
public class ArgusFitoConfig {
    private final ArgusFitoProperties properties;

    private static final int DEFAULT_TIME_OUT = 300000;

    @Value("${http.max-connections-per-route:10}")
    int httpMaxConnectionsPerRoute;

    @Value("${http.max-connections-total:50}")
    int httpMaxConnectionsTotal;

    @Bean
    public RestTemplate argusFitoTemplate(ClientHttpRequestFactory httpClientRequestFactory) {
        return new RestTemplateBuilder()
                .basicAuthentication(properties.getUsername(), properties.getPassword())
                .rootUri(properties.getBaseUrl())
                .messageConverters( new SourceHttpMessageConverter(), new FormHttpMessageConverter(),
                        new StringHttpMessageConverter())
                .requestFactory(() -> httpClientRequestFactory)
                .errorHandler(new ArgusFitoResponseErrorHandler())
                .build();
    }

    @Bean
    PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(httpMaxConnectionsTotal);
        connectionManager.setDefaultMaxPerRoute(httpMaxConnectionsPerRoute);
        return connectionManager;
    }

    @Bean
    public ClientHttpRequestFactory httpClientRequestFactory(Environment env) {

        HttpClient httpClient = HttpClients.custom()
                .useSystemProperties()
                .setConnectionManager(poolingHttpClientConnectionManager())
                .build();

        int timeOutInterval = Integer.parseInt(env.getProperty("http.timeout", String.valueOf(DEFAULT_TIME_OUT)));

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        requestFactory.setConnectTimeout(timeOutInterval);
        requestFactory.setConnectionRequestTimeout(timeOutInterval);
        requestFactory.setReadTimeout(timeOutInterval);

        return requestFactory;
    }
}
