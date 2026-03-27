package it.montano.multipersistencebackend.config.properties;

import it.montano.multipersistencebackend.common.constant.AppConfigConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = AppConfigConstants.PREFIX)
public record AppProperties(String datasource) {}
