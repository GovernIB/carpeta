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
import org.fundaciobit.pluginsib.carpetafront.notib2client.model.Fitxer;
import org.joda.time.DateTime;
/**
 * RespostaConsultaJustificantEnviament
 */



public class RespostaConsultaJustificantEnviament {
  @JsonProperty("error")
  private Boolean error = null;

  @JsonProperty("errorData")
  private DateTime errorData = null;

  @JsonProperty("errorDescripcio")
  private String errorDescripcio = null;

  @JsonProperty("justificant")
  private Fitxer justificant = null;

  public RespostaConsultaJustificantEnviament error(Boolean error) {
    this.error = error;
    return this;
  }

   /**
   * Indica si s&#x27;ha produït algún error en l&#x27;enviament
   * @return error
  **/
  @Schema(example = "true", description = "Indica si s'ha produït algún error en l'enviament")
  public Boolean isError() {
    return error;
  }

  public void setError(Boolean error) {
    this.error = error;
  }

  public RespostaConsultaJustificantEnviament errorData(DateTime errorData) {
    this.errorData = errorData;
    return this;
  }

   /**
   * Data en que s&#x27;ha produït l&#x27;error
   * @return errorData
  **/
  @Schema(description = "Data en que s'ha produït l'error")
  public DateTime getErrorData() {
    return errorData;
  }

  public void setErrorData(DateTime errorData) {
    this.errorData = errorData;
  }

  public RespostaConsultaJustificantEnviament errorDescripcio(String errorDescripcio) {
    this.errorDescripcio = errorDescripcio;
    return this;
  }

   /**
   * Identificador de la notificació a Notib
   * @return errorDescripcio
  **/
  @Schema(example = "java.lang.NullPointerException", description = "Identificador de la notificació a Notib")
  public String getErrorDescripcio() {
    return errorDescripcio;
  }

  public void setErrorDescripcio(String errorDescripcio) {
    this.errorDescripcio = errorDescripcio;
  }

  public RespostaConsultaJustificantEnviament justificant(Fitxer justificant) {
    this.justificant = justificant;
    return this;
  }

   /**
   * Get justificant
   * @return justificant
  **/
  @Schema(description = "")
  public Fitxer getJustificant() {
    return justificant;
  }

  public void setJustificant(Fitxer justificant) {
    this.justificant = justificant;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RespostaConsultaJustificantEnviament respostaConsultaJustificantEnviament = (RespostaConsultaJustificantEnviament) o;
    return Objects.equals(this.error, respostaConsultaJustificantEnviament.error) &&
        Objects.equals(this.errorData, respostaConsultaJustificantEnviament.errorData) &&
        Objects.equals(this.errorDescripcio, respostaConsultaJustificantEnviament.errorDescripcio) &&
        Objects.equals(this.justificant, respostaConsultaJustificantEnviament.justificant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(error, errorData, errorDescripcio, justificant);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RespostaConsultaJustificantEnviament {\n");
    
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("    errorData: ").append(toIndentedString(errorData)).append("\n");
    sb.append("    errorDescripcio: ").append(toIndentedString(errorDescripcio)).append("\n");
    sb.append("    justificant: ").append(toIndentedString(justificant)).append("\n");
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
