package es.caib.carpeta.api.externa.server.certificats.v1.model;

import javax.validation.constraints.*;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets CertificatType
 */
public enum CertificatType {
  
  VALOR("VALOR"),
  
  FITXER("FITXER");

  private String value;

  CertificatType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CertificatType fromValue(String value) {
    for (CertificatType b : CertificatType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

}

