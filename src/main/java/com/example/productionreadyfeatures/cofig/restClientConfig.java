package com.example.productionreadyfeatures.cofig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Configuration
public class restClientConfig {
    @Value("${employeeService.base.url}")
    private String BASE_URL;

    Logger log = LoggerFactory.getLogger(restClientConfig.class);

    @Bean
    @Qualifier("EmployeeRestClient")
    RestClient getEmployeeServiceClient(){
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, (req, res) -> {
                    log.error("500 server error in restClientConfig.class");
                    throw new RuntimeException("Server error occurred");
                })
                .build();
    }
}
