package com.haekyulog.haekyulog;

import com.haekyulog.haekyulog.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AppConfig.class)
@SpringBootApplication
public class HaekyulogApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaekyulogApplication.class, args);
    }

}
