package it.montano.multipersistencebackend.common.constant;

import java.util.List;

public final class AppConfigConstants {

  private AppConfigConstants() {}

  public static final String PREFIX = "app";
  public static final String DATASOURCE = "datasource";
  public static final String POSTGRES = "POSTGRES";
  public static final String MONGODB = "MONGODB";
  public static final List<String> KNOW_DATASOURCES = List.of(POSTGRES, MONGODB);
}
