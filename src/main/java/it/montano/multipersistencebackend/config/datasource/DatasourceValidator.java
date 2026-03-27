package it.montano.multipersistencebackend.config.datasource;

import it.montano.multipersistencebackend.common.constant.AppConfigConstants;
import it.montano.multipersistencebackend.config.properties.AppProperties;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * Validates the 'app.datasource' property at startup. Ensures that the application fails fast if
 * the configuration is invalid, providing a clear explanation of what went wrong.
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class DatasourceValidator {

  private final AppProperties appProperties;
  private static final String PROPERTY_NAME =
      AppConfigConstants.PREFIX + "." + AppConfigConstants.DATASOURCE;
  private static final String INITIAL_MESSAGE = "Evaluating start configuration...\n";
  private static final String SEPARATOR = "--------------------------------------------------\n";
  private static final String SUCCESS_TITLE = "CONFIGURATION VALID\n";
  private static final String ERROR_TITLE = "INVALID CONFIGURATION\n";
  private static final String COMMON_LOG = "Property: %s\nValue: %s\nAllowed: %s\n";
  private static final String SUCCESS_OUTCOME = "Application startup continues...\n";
  private static final String ERROR_OUTCOME =
      "Application will terminate to prevent inconsistent state.\n";

  /**
   * Performs validation of the active datasource type. Throws an IllegalStateException if
   * configured with an unsupported value.
   */
  @PostConstruct
  public void validateDatasource() {
    if (!AppConfigConstants.KNOW_DATASOURCES.contains(appProperties.datasource())) {
      String errorMessage = buildMessage(ERROR_TITLE, appProperties.datasource(), ERROR_OUTCOME);
      log.error(errorMessage);
      throw new IllegalStateException(errorMessage);
    }
    String startingMessage =
        buildMessage(SUCCESS_TITLE, appProperties.datasource(), SUCCESS_OUTCOME);
    log.info(startingMessage);
  }

  private String buildMessage(
      @NonNull String logTitle, @Nullable String datasource, @NonNull String resultMessage) {
    return INITIAL_MESSAGE
        + SEPARATOR
        + logTitle
        + String.format(
            COMMON_LOG,
            PROPERTY_NAME,
            datasource,
            String.join(", ", AppConfigConstants.KNOW_DATASOURCES))
        + resultMessage
        + SEPARATOR;
  }
}
