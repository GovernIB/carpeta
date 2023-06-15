package es.caib.carpeta.api.externa.certificats;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author fbosch
 *
 */
@Schema(name = "CertificatType", enumAsRef = true)
public enum CertificatType {
    VALOR("VALOR"), FITXER("FITXER");
    

    private String value;
    
    CertificatType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
      return String.valueOf(value);
    }
    @JsonCreator
    public static CertificatType fromValue(String input) {
      for (CertificatType b : CertificatType.values()) {
        if (b.value.equals(input)) {
          return b;
        }
      }
      return null;
    }

}
