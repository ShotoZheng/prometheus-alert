package com.shoto.prometheus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author shoto
 */
@EnableAspectJAutoProxy
@ConfigurationPropertiesScan
@ComponentScan(basePackages = "com.shoto.prometheus.*")
@SpringBootApplication
public class PrometheusAlertApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrometheusAlertApplication.class, args);
    }

}
