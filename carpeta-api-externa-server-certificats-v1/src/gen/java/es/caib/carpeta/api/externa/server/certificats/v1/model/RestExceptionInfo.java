package es.caib.carpeta.api.externa.server.certificats.v1.model;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
  * Estructura de dades utilitzada per passar informació d'un error
 **/
@ApiModel(description="Estructura de dades utilitzada per passar informació d'un error")

public class RestExceptionInfo  {
  
  @ApiModelProperty(value = "Codi intern de l'error. Si l'Aplicació no gestiona codis d'error llavors val null.")
 /**
   * Codi intern de l'error. Si l'Aplicació no gestiona codis d'error llavors val null.
  **/
  private Integer errorCode;

  @ApiModelProperty(required = true, value = "Missatge de l'error")
 /**
   * Missatge de l'error
  **/
  private String errorMessage;

  @ApiModelProperty(value = "Stacktrace de l'excepció")
 /**
   * Stacktrace de l'excepció
  **/
  private String stackTrace;

  @ApiModelProperty(value = "Stacktrace de l'excepció causant de l'error si n'hi hagués.")
 /**
   * Stacktrace de l'excepció causant de l'error si n'hi hagués.
  **/
  private String stackTraceCause;

  @ApiModelProperty(value = "Indica el camp en que hi ha un error de validació.")
 /**
   * Indica el camp en que hi ha un error de validació.
  **/
  private String field;
 /**
   * Codi intern de l&#39;error. Si l&#39;Aplicació no gestiona codis d&#39;error llavors val null.
   * @return errorCode
  **/
  @JsonProperty("errorCode")
  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  public RestExceptionInfo errorCode(Integer errorCode) {
    this.errorCode = errorCode;
    return this;
  }

 /**
   * Missatge de l&#39;error
   * @return errorMessage
  **/
  @JsonProperty("errorMessage")
  @NotNull
  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public RestExceptionInfo errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

 /**
   * Stacktrace de l&#39;excepció
   * @return stackTrace
  **/
  @JsonProperty("stackTrace")
  public String getStackTrace() {
    return stackTrace;
  }

  public void setStackTrace(String stackTrace) {
    this.stackTrace = stackTrace;
  }

  public RestExceptionInfo stackTrace(String stackTrace) {
    this.stackTrace = stackTrace;
    return this;
  }

 /**
   * Stacktrace de l&#39;excepció causant de l&#39;error si n&#39;hi hagués.
   * @return stackTraceCause
  **/
  @JsonProperty("stackTraceCause")
  public String getStackTraceCause() {
    return stackTraceCause;
  }

  public void setStackTraceCause(String stackTraceCause) {
    this.stackTraceCause = stackTraceCause;
  }

  public RestExceptionInfo stackTraceCause(String stackTraceCause) {
    this.stackTraceCause = stackTraceCause;
    return this;
  }

 /**
   * Indica el camp en que hi ha un error de validació.
   * @return field
  **/
  @JsonProperty("field")
  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public RestExceptionInfo field(String field) {
    this.field = field;
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
    RestExceptionInfo restExceptionInfo = (RestExceptionInfo) o;
    return Objects.equals(this.errorCode, restExceptionInfo.errorCode) &&
        Objects.equals(this.errorMessage, restExceptionInfo.errorMessage) &&
        Objects.equals(this.stackTrace, restExceptionInfo.stackTrace) &&
        Objects.equals(this.stackTraceCause, restExceptionInfo.stackTraceCause) &&
        Objects.equals(this.field, restExceptionInfo.field);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorCode, errorMessage, stackTrace, stackTraceCause, field);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestExceptionInfo {\n");
    
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    stackTrace: ").append(toIndentedString(stackTrace)).append("\n");
    sb.append("    stackTraceCause: ").append(toIndentedString(stackTraceCause)).append("\n");
    sb.append("    field: ").append(toIndentedString(field)).append("\n");
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

