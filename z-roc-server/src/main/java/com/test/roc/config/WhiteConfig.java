package com.test.roc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author cosmo
 */
@Data
@ConfigurationProperties("setting")
@Component
public class WhiteConfig {

    private List<String> whiteUrls;

}