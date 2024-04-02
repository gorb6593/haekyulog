package com.haekyulog.haekyulog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "haekyulog")
public class AppConfig {

    public String jwtKey;
}
