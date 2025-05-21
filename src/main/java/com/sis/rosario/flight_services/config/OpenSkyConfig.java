package com.sis.rosario.flight_services.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

@Configuration
public class OpenSkyConfig {

    @Value("${opensky.api.url}")
    private String apiUrl;

    @Bean("openSkyWebClient") 
    public WebClient openSkyWebClient(WebClient.Builder builder) {
        HttpClient httpClient = HttpClient.create()
                .proxy(proxy -> proxy
                    .type(ProxyProvider.Proxy.HTTP)
                    .host("proxyespecial.svc.rosario.gov.ar")
                    .port(3128)
                    .username("tcastel1")
                    .password(s -> "Tomas2024"));
        
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(configurer -> 
                    configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024) // 10 MB
                )
                .build();
        
        return builder
            .baseUrl(apiUrl)
            .exchangeStrategies(strategies) 
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();
    }
}