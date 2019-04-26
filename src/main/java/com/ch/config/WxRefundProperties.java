package com.ch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wxrefund")
@Data
public class WxRefundProperties {

    private String certificate;

}
