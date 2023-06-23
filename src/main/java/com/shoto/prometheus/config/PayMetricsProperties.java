package com.shoto.prometheus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "monitor.metrics.pay")
public class PayMetricsProperties {

    /**
     * 支付 PV事件
     */
    private String payEventPv;
}
