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

package es.caib.carpeta.apiexterna.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.joda.time.DateTime;
/**
 * Acces
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-04-08T12:18:38.160401700+02:00[Europe/Paris]")
public class Acces {
  @JsonProperty("proveidor")
  private String proveidor = null;

  @JsonProperty("metodeAutenticacio")
  private String metodeAutenticacio = null;

  @JsonProperty("qaa")
  private Integer qaa = null;

  @JsonProperty("data")
  private DateTime data = null;

  @JsonProperty("idioma")
  private String idioma = null;

  @JsonProperty("entitat")
  private String entitat = null;

  @JsonProperty("tipus")
  private String tipus = null;

  @JsonProperty("plugin")
  private String plugin = null;

  @JsonProperty("idSessio")
  private String idSessio = null;

  public Acces proveidor(String proveidor) {
    this.proveidor = proveidor;
    return this;
  }

   /**
   * Get proveidor
   * @return proveidor
  **/
  @Schema(description = "")
  public String getProveidor() {
    return proveidor;
  }

  public void setProveidor(String proveidor) {
    this.proveidor = proveidor;
  }

  public Acces metodeAutenticacio(String metodeAutenticacio) {
    this.metodeAutenticacio = metodeAutenticacio;
    return this;
  }

   /**
   * Get metodeAutenticacio
   * @return metodeAutenticacio
  **/
  @Schema(description = "")
  public String getMetodeAutenticacio() {
    return metodeAutenticacio;
  }

  public void setMetodeAutenticacio(String metodeAutenticacio) {
    this.metodeAutenticacio = metodeAutenticacio;
  }

  public Acces qaa(Integer qaa) {
    this.qaa = qaa;
    return this;
  }

   /**
   * Get qaa
   * @return qaa
  **/
  @Schema(description = "")
  public Integer getQaa() {
    return qaa;
  }

  public void setQaa(Integer qaa) {
    this.qaa = qaa;
  }

  public Acces data(DateTime data) {
    this.data = data;
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @Schema(description = "")
  public DateTime getData() {
    return data;
  }

  public void setData(DateTime data) {
    this.data = data;
  }

  public Acces idioma(String idioma) {
    this.idioma = idioma;
    return this;
  }

   /**
   * Get idioma
   * @return idioma
  **/
  @Schema(description = "")
  public String getIdioma() {
    return idioma;
  }

  public void setIdioma(String idioma) {
    this.idioma = idioma;
  }

  public Acces entitat(String entitat) {
    this.entitat = entitat;
    return this;
  }

   /**
   * Get entitat
   * @return entitat
  **/
  @Schema(description = "")
  public String getEntitat() {
    return entitat;
  }

  public void setEntitat(String entitat) {
    this.entitat = entitat;
  }

  public Acces tipus(String tipus) {
    this.tipus = tipus;
    return this;
  }

   /**
   * Get tipus
   * @return tipus
  **/
  @Schema(description = "")
  public String getTipus() {
    return tipus;
  }

  public void setTipus(String tipus) {
    this.tipus = tipus;
  }

  public Acces plugin(String plugin) {
    this.plugin = plugin;
    return this;
  }

   /**
   * Get plugin
   * @return plugin
  **/
  @Schema(description = "")
  public String getPlugin() {
    return plugin;
  }

  public void setPlugin(String plugin) {
    this.plugin = plugin;
  }

  public Acces idSessio(String idSessio) {
    this.idSessio = idSessio;
    return this;
  }

   /**
   * Get idSessio
   * @return idSessio
  **/
  @Schema(description = "")
  public String getIdSessio() {
    return idSessio;
  }

  public void setIdSessio(String idSessio) {
    this.idSessio = idSessio;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Acces acces = (Acces) o;
    return Objects.equals(this.proveidor, acces.proveidor) &&
        Objects.equals(this.metodeAutenticacio, acces.metodeAutenticacio) &&
        Objects.equals(this.qaa, acces.qaa) &&
        Objects.equals(this.data, acces.data) &&
        Objects.equals(this.idioma, acces.idioma) &&
        Objects.equals(this.entitat, acces.entitat) &&
        Objects.equals(this.tipus, acces.tipus) &&
        Objects.equals(this.plugin, acces.plugin) &&
        Objects.equals(this.idSessio, acces.idSessio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(proveidor, metodeAutenticacio, qaa, data, idioma, entitat, tipus, plugin, idSessio);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Acces {\n");
    
    sb.append("    proveidor: ").append(toIndentedString(proveidor)).append("\n");
    sb.append("    metodeAutenticacio: ").append(toIndentedString(metodeAutenticacio)).append("\n");
    sb.append("    qaa: ").append(toIndentedString(qaa)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    idioma: ").append(toIndentedString(idioma)).append("\n");
    sb.append("    entitat: ").append(toIndentedString(entitat)).append("\n");
    sb.append("    tipus: ").append(toIndentedString(tipus)).append("\n");
    sb.append("    plugin: ").append(toIndentedString(plugin)).append("\n");
    sb.append("    idSessio: ").append(toIndentedString(idSessio)).append("\n");
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
