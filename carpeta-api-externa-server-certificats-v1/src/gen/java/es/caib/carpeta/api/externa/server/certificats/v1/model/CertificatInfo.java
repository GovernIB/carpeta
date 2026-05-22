package es.caib.carpeta.api.externa.server.certificats.v1.model;

import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CertificatInfo  {
  
  @ApiModelProperty(value = "")
  private Boolean teCertificat;

  @ApiModelProperty(value = "")
  private String administrationId;
 /**
   * Get teCertificat
   * @return teCertificat
  **/
  @JsonProperty("teCertificat")
  public Boolean getTeCertificat() {
    return teCertificat;
  }

  public void setTeCertificat(Boolean teCertificat) {
    this.teCertificat = teCertificat;
  }

  public CertificatInfo teCertificat(Boolean teCertificat) {
    this.teCertificat = teCertificat;
    return this;
  }

 /**
   * Get administrationId
   * @return administrationId
  **/
  @JsonProperty("administrationId")
  public String getAdministrationId() {
    return administrationId;
  }

  public void setAdministrationId(String administrationId) {
    this.administrationId = administrationId;
  }

  public CertificatInfo administrationId(String administrationId) {
    this.administrationId = administrationId;
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
    CertificatInfo certificatInfo = (CertificatInfo) o;
    return Objects.equals(this.teCertificat, certificatInfo.teCertificat) &&
        Objects.equals(this.administrationId, certificatInfo.administrationId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(teCertificat, administrationId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CertificatInfo {\n");
    
    sb.append("    teCertificat: ").append(toIndentedString(teCertificat)).append("\n");
    sb.append("    administrationId: ").append(toIndentedString(administrationId)).append("\n");
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

