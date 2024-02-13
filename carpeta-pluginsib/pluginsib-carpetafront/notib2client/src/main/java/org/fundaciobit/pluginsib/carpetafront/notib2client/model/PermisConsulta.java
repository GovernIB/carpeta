/*
 * API Interna NOTIB
 * API Interna de NOTIB
 *
 * OpenAPI spec version: v2.0
 * Contact: limit@limit.es
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package org.fundaciobit.pluginsib.carpetafront.notib2client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * Objecte amb les dades necessàries per donar el permís
 */
@Schema(description = "Objecte amb les dades necessàries per donar el permís")


public class PermisConsulta {
  @JsonProperty("codiDir3Entitat")
  private String codiDir3Entitat = null;

  @JsonProperty("permisConsulta")
  private Boolean permisConsulta = null;

  @JsonProperty("procedimentCodi")
  private String procedimentCodi = null;

  @JsonProperty("usuariCodi")
  private String usuariCodi = null;

  public PermisConsulta codiDir3Entitat(String codiDir3Entitat) {
    this.codiDir3Entitat = codiDir3Entitat;
    return this;
  }

   /**
   * Codi dir3 de l&#x27;entitat en la que es vol donar permís de consulta
   * @return codiDir3Entitat
  **/
  @Schema(example = "A04003003", description = "Codi dir3 de l'entitat en la que es vol donar permís de consulta")
  public String getCodiDir3Entitat() {
    return codiDir3Entitat;
  }

  public void setCodiDir3Entitat(String codiDir3Entitat) {
    this.codiDir3Entitat = codiDir3Entitat;
  }

  public PermisConsulta permisConsulta(Boolean permisConsulta) {
    this.permisConsulta = permisConsulta;
    return this;
  }

   /**
   * Indica si es vol donar o treure el permís de consulta
   * @return permisConsulta
  **/
  @Schema(example = "true", description = "Indica si es vol donar o treure el permís de consulta")
  public Boolean isPermisConsulta() {
    return permisConsulta;
  }

  public void setPermisConsulta(Boolean permisConsulta) {
    this.permisConsulta = permisConsulta;
  }

  public PermisConsulta procedimentCodi(String procedimentCodi) {
    this.procedimentCodi = procedimentCodi;
    return this;
  }

   /**
   * Codi SIA del procediment sobre el que es vol donar permís de consulta
   * @return procedimentCodi
  **/
  @Schema(example = "666666", description = "Codi SIA del procediment sobre el que es vol donar permís de consulta")
  public String getProcedimentCodi() {
    return procedimentCodi;
  }

  public void setProcedimentCodi(String procedimentCodi) {
    this.procedimentCodi = procedimentCodi;
  }

  public PermisConsulta usuariCodi(String usuariCodi) {
    this.usuariCodi = usuariCodi;
    return this;
  }

   /**
   * Codi de l&#x27;usuari al que es vol donar permís de consulta
   * @return usuariCodi
  **/
  @Schema(example = "u000000", description = "Codi de l'usuari al que es vol donar permís de consulta")
  public String getUsuariCodi() {
    return usuariCodi;
  }

  public void setUsuariCodi(String usuariCodi) {
    this.usuariCodi = usuariCodi;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PermisConsulta permisConsulta = (PermisConsulta) o;
    return Objects.equals(this.codiDir3Entitat, permisConsulta.codiDir3Entitat) &&
        Objects.equals(this.permisConsulta, permisConsulta.permisConsulta) &&
        Objects.equals(this.procedimentCodi, permisConsulta.procedimentCodi) &&
        Objects.equals(this.usuariCodi, permisConsulta.usuariCodi);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiDir3Entitat, permisConsulta, procedimentCodi, usuariCodi);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PermisConsulta {\n");
    
    sb.append("    codiDir3Entitat: ").append(toIndentedString(codiDir3Entitat)).append("\n");
    sb.append("    permisConsulta: ").append(toIndentedString(permisConsulta)).append("\n");
    sb.append("    procedimentCodi: ").append(toIndentedString(procedimentCodi)).append("\n");
    sb.append("    usuariCodi: ").append(toIndentedString(usuariCodi)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
