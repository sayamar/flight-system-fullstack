package com.jpmc.test_interview.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pdf")
@Setter
@Getter
public class FlightConfig {

    private String path;
    private String footer;
}
