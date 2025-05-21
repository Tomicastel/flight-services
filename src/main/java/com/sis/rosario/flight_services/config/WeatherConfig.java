package com.sis.rosario.flight_services.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;


@Configuration
public class WeatherConfig {

    @Value("${weather.api.url}")
    private String apiUrl;

    @Bean("weatherWebClient") 
    public WebClient weatherWebClient(WebClient.Builder builder) {
        HttpClient httpClient = HttpClient.create()
            .proxy(proxy -> proxy
                .type(ProxyProvider.Proxy.HTTP)
                .host("proxyespecial.svc.rosario.gov.ar")
                .port(3128)
                .username("tcastel1")
                .password(s -> "Tomas2024"));

        return builder
            .baseUrl(apiUrl)
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .defaultHeader("Accept", "application/json")
            .build();
    }
}