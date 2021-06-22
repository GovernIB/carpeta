package es.caib.apodera.client;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author anadal
 *
 */
public class TipoApoderamiento {

  public static final TipoApoderamiento GENERAL = new TipoApoderamiento("1", "0", "GENERAL");
  public static final TipoApoderamiento ADMINISTRACION_GENERAL_DEL_ESTADO = new TipoApoderamiento(
      "2", "0", "ADMINISTRACION_GENERAL_DEL_ESTADO");
  public static final TipoApoderamiento ORGANISMO_PUBLICO = new TipoApoderamiento("2", "1", "ORGANISMO_PUBLICO");
  public static final TipoApoderamiento ADMINISTRACION_AUTONOMICA = new TipoApoderamiento("2",
      "2", "ADMINISTRACION_AUTONOMICA");
  public static final TipoApoderamiento ADMINISTRACION_LOCAL = new TipoApoderamiento("2", "3", "ADMINISTRACION_LOCAL");
  public static final TipoApoderamiento UNIVERSIDADES = new TipoApoderamiento("2", "4", "UNIVERSIDADES");
  public static final TipoApoderamiento OTRAS_INSTITUCIONES = new TipoApoderamiento("2", "5", "OTRAS_INSTITUCIONES");
  public static final TipoApoderamiento TODAS_LAS_ADMINISTRACIONES_PUBLICAS = new TipoApoderamiento(
      "3", "0", "TODAS_LAS_ADMINISTRACIONES_PUBLICAS");
  public static final TipoApoderamiento PROCEDIMIENTOS_Y_O_TRAMITES = new TipoApoderamiento(
      "3", "1", "PROCEDIMIENTOS_Y_O_TRAMITES");
  
  

  protected final String tipo;

  protected final String subtipo;
  
  protected final String descripcion; 

  private static final Map<String, TipoApoderamiento> tipos = new HashMap<String, TipoApoderamiento>();
  
  static {
    registerTipoApoderamiento(GENERAL);
    registerTipoApoderamiento(ADMINISTRACION_GENERAL_DEL_ESTADO);
    registerTipoApoderamiento(ORGANISMO_PUBLICO);
    registerTipoApoderamiento(ADMINISTRACION_AUTONOMICA);
    registerTipoApoderamiento(ADMINISTRACION_LOCAL);
    registerTipoApoderamiento(UNIVERSIDADES);
    registerTipoApoderamiento(OTRAS_INSTITUCIONES);
    registerTipoApoderamiento(TODAS_LAS_ADMINISTRACIONES_PUBLICAS);
    registerTipoApoderamiento(PROCEDIMIENTOS_Y_O_TRAMITES);
  }

  private static void registerTipoApoderamiento(TipoApoderamiento ta) {
    tipos.put(ta.tipo + "_" + ta.subtipo, ta);
  }

  public static TipoApoderamiento getTipoApoderamiento(String tipo, String subtipo) {
    return tipos.get(tipo + "_" + subtipo);
  }

  /**
   * @param tipo
   * @param subtipo
   */
  private TipoApoderamiento(String tipo, String subtipo, String descripcion) {
    super();
    this.tipo = tipo;
    this.subtipo = subtipo;
    this.descripcion = descripcion;    
  }

  public String getTipo() {
    return tipo;
  }

  public String getSubtipo() {
    return subtipo;
  }

  public String getDescripcion() {
    return descripcion;
  };

}
