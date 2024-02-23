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
import java.util.Date;
/**
 * Informació per a l&#x27;enviament SIR
 */
@Schema(description = "Informació per a l'enviament SIR")


public class Sir {
  @JsonProperty("dataRecepcio")
  private Date dataRecepcio = null;

  @JsonProperty("dataRegistreDesti")
  private Date dataRegistreDesti = null;

  public Sir dataRecepcio(Date dataRecepcio) {
    this.dataRecepcio = dataRecepcio;
    return this;
  }

   /**
   * Data en que s&#x27;ha recepcionat el registre
   * @return dataRecepcio
  **/
  @Schema(description = "Data en que s'ha recepcionat el registre")
  public Date getDataRecepcio() {
    return dataRecepcio;
  }

  public void setDataRecepcio(Date dataRecepcio) {
    this.dataRecepcio = dataRecepcio;
  }

  public Sir dataRegistreDesti(Date dataRegistreDesti) {
    this.dataRegistreDesti = dataRegistreDesti;
    return this;
  }

   /**
   * Data en que s&#x27;ha registrat en el registre de destí
   * @return dataRegistreDesti
  **/
  @Schema(description = "Data en que s'ha registrat en el registre de destí")
  public Date getDataRegistreDesti() {
    return dataRegistreDesti;
  }

  public void setDataRegistreDesti(Date dataRegistreDesti) {
    this.dataRegistreDesti = dataRegistreDesti;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sir sir = (Sir) o;
    return Objects.equals(this.dataRecepcio, sir.dataRecepcio) &&
        Objects.equals(this.dataRegistreDesti, sir.dataRegistreDesti);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataRecepcio, dataRegistreDesti);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Sir {\n");
    
    sb.append("    dataRecepcio: ").append(toIndentedString(dataRecepcio)).append("\n");
    sb.append("    dataRegistreDesti: ").append(toIndentedString(dataRegistreDesti)).append("\n");
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
