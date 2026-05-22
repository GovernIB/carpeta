package es.caib.carpeta.api.externa.server.certificats.v1.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import es.caib.carpeta.api.externa.server.certificats.v1.model.CertificatFileInfo;
import es.caib.carpeta.api.externa.server.certificats.v1.model.CertificatType;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CertificatBean  {
  
  @ApiModelProperty(value = "")
  @Valid
  private CertificatType tipus;

  @ApiModelProperty(value = "")
  @Valid
  private CertificatFileInfo fitxer;

  @ApiModelProperty(value = "")
  private String url;
 /**
   * Get tipus
   * @return tipus
  **/
  @JsonProperty("tipus")
  public CertificatType getTipus() {
    return tipus;
  }

  public void setTipus(CertificatType tipus) {
    this.tipus = tipus;
  }

  public CertificatBean tipus(CertificatType tipus) {
    this.tipus = tipus;
    return this;
  }

 /**
   * Get fitxer
   * @return fitxer
  **/
  @JsonProperty("fitxer")
  public CertificatFileInfo getFitxer() {
    return fitxer;
  }

  public void setFitxer(CertificatFileInfo fitxer) {
    this.fitxer = fitxer;
  }

  public CertificatBean fitxer(CertificatFileInfo fitxer) {
    this.fitxer = fitxer;
    return this;
  }

 /**
   * Get url
   * @return url
  **/
  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public CertificatBean url(String url) {
    this.url = url;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CertificatBean certificatBean = (CertificatBean) o;
    return Objects.equals(this.tipus, certificatBean.tipus) &&
        Objects.equals(this.fitxer, certificatBean.fitxer) &&
        Objects.equals(this.url, certificatBean.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tipus, fitxer, url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CertificatBean {\n");
    
    sb.append("    tipus: ").append(toIndentedString(tipus)).append("\n");
    sb.append("    fitxer: ").append(toIndentedString(fitxer)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

