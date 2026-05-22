package es.caib.carpeta.api.externa.server.certificats.v1.model;

import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CertificatFileInfo  {
  
  @ApiModelProperty(value = "")
  private String nom;

  @ApiModelProperty(value = "")
  private String mime;

  @ApiModelProperty(value = "")
  private Integer length;

  @ApiModelProperty(required = true, value = "")
  private byte[] bytes;
 /**
   * Get nom
   * @return nom
  **/
  @JsonProperty("nom")
  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public CertificatFileInfo nom(String nom) {
    this.nom = nom;
    return this;
  }

 /**
   * Get mime
   * @return mime
  **/
  @JsonProperty("mime")
  public String getMime() {
    return mime;
  }

  public void setMime(String mime) {
    this.mime = mime;
  }

  public CertificatFileInfo mime(String mime) {
    this.mime = mime;
    return this;
  }

 /**
   * Get length
   * @return length
  **/
  @JsonProperty("length")
  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public CertificatFileInfo length(Integer length) {
    this.length = length;
    return this;
  }

 /**
   * Get bytes
   * @return bytes
  **/
  @JsonProperty("bytes")
  @NotNull
  public byte[] getBytes() {
    return bytes;
  }

  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }

  public CertificatFileInfo bytes(byte[] bytes) {
    this.bytes = bytes;
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
    CertificatFileInfo certificatFileInfo = (CertificatFileInfo) o;
    return Objects.equals(this.nom, certificatFileInfo.nom) &&
        Objects.equals(this.mime, certificatFileInfo.mime) &&
        Objects.equals(this.length, certificatFileInfo.length) &&
        Objects.equals(this.bytes, certificatFileInfo.bytes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nom, mime, length, bytes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CertificatFileInfo {\n");
    
    sb.append("    nom: ").append(toIndentedString(nom)).append("\n");
    sb.append("    mime: ").append(toIndentedString(mime)).append("\n");
    sb.append("    length: ").append(toIndentedString(length)).append("\n");
    sb.append("    bytes: ").append(toIndentedString(bytes)).append("\n");
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

