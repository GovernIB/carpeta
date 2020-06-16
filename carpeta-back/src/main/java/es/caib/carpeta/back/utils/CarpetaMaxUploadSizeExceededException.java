package es.caib.carpeta.back.utils;

import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 
 * @author anadal
 *
 */
public class CarpetaMaxUploadSizeExceededException extends MaxUploadSizeExceededException {

  final String msgCode;

  /**
   * @param msg
   */
  public CarpetaMaxUploadSizeExceededException(Throwable cause, long maxSize, String msgCode) {
    super(maxSize, cause);    
    this.msgCode = msgCode;
  }

  public String getMsgCode() {
    return msgCode;
  }

}
