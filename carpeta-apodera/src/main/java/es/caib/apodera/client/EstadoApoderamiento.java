package es.caib.apodera.client;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author anadal
 *
 */
public class EstadoApoderamiento {

  protected static final Map<String, EstadoApoderamiento> estados = new HashMap<String, EstadoApoderamiento>();

  static {
    estados.put("1", new EstadoApoderamiento("1", "Sin autorizar",
        "Apoderamiento para el cual el apoderado debe expresar explícitamente su consentimiento."));
    estados.put("2", new EstadoApoderamiento("2", "Autorizado", "Apoderamiento vigente."));
    estados.put("3",
        new EstadoApoderamiento("3", "Revocado", "Apoderamiento revocado por el poderdante."));
    estados.put("4", new EstadoApoderamiento("4", "Renunciado",
        "Apoderamiento renunciado por el apoderado."));
    estados.put("5", new EstadoApoderamiento("5", "Caducado",
        "Apoderamiento que ha superado el periodo de vigencia."));
    estados.put("6", new EstadoApoderamiento("6", "Cancelado",
        "Apoderamiento que ha superado el periodo de espera de confirmación por parte del apoderado o el tiempo de espera de aprobación del alta del apoderamiento por parte del asesor jurídico."));

    estados.put("7", new EstadoApoderamiento("7", "Pendiente (a extinguir)",
        "Apoderamiento realizado en representación de una persona física o jurídica que necesita ser bastanteado por parte del asesor jurídico del organismo."));
    estados.put("8", new EstadoApoderamiento("8", "Pendiente de Modificación (a extinguir)",
        "Apoderamiento realizado en representación de una persona física o jurídica que ha sido modificado y que necesita ser bastanteado por parte del asesor jurídico del organismo para la autorización definitiva."));
    estados.put("9", new EstadoApoderamiento("9", "Pendiente de Revocación (a extinguir)",
        "Apoderamiento realizado en representación de una persona física o jurídica que ha sido revocado y que necesita ser bastanteado por parte del asesor jurídico del organismo para la autorización definitiva."));
    estados.put("10", new EstadoApoderamiento("10", "Denegado (a extinguir)",
        "Apoderamiento cuyo registro ha sido denegado por el asesor jurídico del organismo competente, al no cumplir el poderdante los requerimientos necesarios para ser representante de la empresa que refleja el apoderamiento."));
    estados.put("11", new EstadoApoderamiento("11", "Pendiente de Subsanación (a extinguir)",
        "Apoderamiento realizado en representación de una persona física o jurídica que ha sido rechazado por parte del asesor jurídico del organismo."));
  }

  protected final String codi;
  protected final String estat;
  protected final String descripcio;

  /**
   * @param codi
   * @param estat
   * @param descripcio
   */
  public EstadoApoderamiento(String codi, String estat, String descripcio) {
    super();
    this.codi = codi;
    this.estat = estat;
    this.descripcio = descripcio;
  }

  public String getCodi() {
    return codi;
  }

  public String getEstat() {
    return estat;
  }

  public String getDescripcio() {
    return descripcio;
  }


  public static final EstadoApoderamiento getEstadoApoderamientoByCodi(String codi) {
    return estados.get(codi);
  }

}
