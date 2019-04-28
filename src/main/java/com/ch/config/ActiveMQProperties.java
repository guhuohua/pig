package com.ch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "activemq")
@Data
public class ActiveMQProperties {

    private Long cancel;

    private Long delivery;

}
