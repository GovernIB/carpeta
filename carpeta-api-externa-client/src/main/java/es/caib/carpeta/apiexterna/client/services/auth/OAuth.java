/*
 * API REST EXTERNA de Carpeta
 * Conjunt de Serveis REST de Carpeta per ser accedits des de l'exterior
 *
 * OpenAPI spec version: 1.0.0
 * Contact: governdigital.carpeta@fundaciobit.org
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package es.caib.carpeta.apiexterna.client.services.auth;

import es.caib.carpeta.apiexterna.client.services.Pair;

import java.util.Map;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-04-08T12:18:38.160401700+02:00[Europe/Paris]")public class OAuth implements Authentication {
  private String accessToken;

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  @Override
  public void applyToParams(List<Pair> queryParams, Map<String, String> headerParams) {
    if (accessToken != null) {
      headerParams.put("Authorization", "Bearer " + accessToken);
    }
  }
}
