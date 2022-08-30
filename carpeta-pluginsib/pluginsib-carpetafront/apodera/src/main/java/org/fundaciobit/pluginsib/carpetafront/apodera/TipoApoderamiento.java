package org.fundaciobit.pluginsib.carpetafront.apodera;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author anadal
 *
 */
public class TipoApoderamiento {

    public static final TipoApoderamiento TIPO_1_GENERAL = new TipoApoderamiento("1", "0", "General");
    public static final TipoApoderamiento TIPO_2_ADMINISTRACION_GENERAL_DEL_ESTADO = new TipoApoderamiento("2", "0",
            "Administración General del Estado");
    public static final TipoApoderamiento TIPO_2_ORGANISMO_PUBLICO = new TipoApoderamiento("2", "1",
            "Organismo público");
    public static final TipoApoderamiento TIPO_2_ADMINISTRACION_AUTONOMICA = new TipoApoderamiento("2", "2",
            "Administración Autonómica");
    public static final TipoApoderamiento TIPO_2_ADMINISTRACION_LOCAL = new TipoApoderamiento("2", "3",
            "Administración Local");
    public static final TipoApoderamiento TIPO_2_UNIVERSIDADES = new TipoApoderamiento("2", "4", "Universidades");
    public static final TipoApoderamiento TIPO_2_OTRAS_INSTITUCIONES = new TipoApoderamiento("2", "5",
            "Otras instituciones");
    public static final TipoApoderamiento TIPO_2_ADMINISTRACION_GENERAL_DEL_ESTADO_2 = new TipoApoderamiento("2", "11",
            "Administración general del estado");
    public static final TipoApoderamiento TIPO_2_ADMINISTRACION_PUBLICA = new TipoApoderamiento("2", "12",
            "Administración pública");
    public static final TipoApoderamiento TIPO_2_ORGANISMO_PUBLICO_2 = new TipoApoderamiento("2", "13",
            "Organismo público");
    public static final TipoApoderamiento TIPO_2_UNIVERSIDAD = new TipoApoderamiento("2", "14", "Universidad");
    public static final TipoApoderamiento TIPO_2_OTRAS_INSTITUCIONES_2 = new TipoApoderamiento("2", "15",
            "Otras Instituciones");
    public static final TipoApoderamiento TIPO_2_COMUNIDAD_AUTONOMA = new TipoApoderamiento("2", "21",
            "Comunidad Autónoma");
    public static final TipoApoderamiento TIPO_2_UNIVERSIDAD_2 = new TipoApoderamiento("2", "22", "Universidad");
    public static final TipoApoderamiento TIPO_2_ORGANISMO_PUBLICO_3 = new TipoApoderamiento("2", "23",
            "Organismo público");
    public static final TipoApoderamiento TIPO_2_ENTIDAD_LOCAL = new TipoApoderamiento("2", "31", "Entidad Local");
    public static final TipoApoderamiento TIPO_2_OTRAS_INSTITUCIONES_3 = new TipoApoderamiento("2", "32",
            "Otras Instituciones");
    public static final TipoApoderamiento TIPO_3_TODAS_LAS_ADMINISTRACIONES_PUBLICAS = new TipoApoderamiento("3", "0",
            "Todas las administraciones públicas");
    public static final TipoApoderamiento TIPO_3_PROCEDIMIENTOS_Y_O_TRAMITES = new TipoApoderamiento("3", "1",
            "Procedimientos y/o trámites");
    public static final TipoApoderamiento TIPO_3_ADMINISTRACION_GENERAL_DEL_ESTADO = new TipoApoderamiento("3", "11",
            "Administración general del Estado");
    public static final TipoApoderamiento TIPO_3_ORGANISMO_PUBLICO_2 = new TipoApoderamiento("3", "12",
            "Organismo público");
    public static final TipoApoderamiento TIPO_3_UNIVERSIDAD_2 = new TipoApoderamiento("3", "13", "Universidad");
    public static final TipoApoderamiento TIPO_3_OTRAS_INSTITUCIONES_2 = new TipoApoderamiento("3", "14",
            "Otras Instituciones");
    public static final TipoApoderamiento TIPO_3_ORGANISMO_PUBLICO = new TipoApoderamiento("3", "21",
            "Organismo público");
    public static final TipoApoderamiento TIPO_3_UNIVERSIDAD = new TipoApoderamiento("3", "22", "Universidad");
    public static final TipoApoderamiento TIPO_3_OTRAS_INSTITUCIONES_3 = new TipoApoderamiento("3", "23",
            "Otras Instituciones");
    public static final TipoApoderamiento TIPO_3_ENTIDAD_LOCAL = new TipoApoderamiento("3", "31", "Entidad Local");
    public static final TipoApoderamiento TIPO_3_OTRAS_INSTITUCIONES = new TipoApoderamiento("3", "32",
            "Otras Instituciones");

    protected final String tipo;

    protected final String subtipo;

    protected final String descripcion;

    private static final Map<String, TipoApoderamiento> tipos = new HashMap<String, TipoApoderamiento>();

    static {

        TipoApoderamiento[] alltipos = new TipoApoderamiento[] { TIPO_1_GENERAL,
                TIPO_2_ADMINISTRACION_GENERAL_DEL_ESTADO, TIPO_2_ORGANISMO_PUBLICO, TIPO_2_ADMINISTRACION_AUTONOMICA,
                TIPO_2_ADMINISTRACION_LOCAL, TIPO_2_UNIVERSIDADES, TIPO_2_OTRAS_INSTITUCIONES,
                TIPO_2_ADMINISTRACION_GENERAL_DEL_ESTADO_2, TIPO_2_ADMINISTRACION_PUBLICA, TIPO_2_ORGANISMO_PUBLICO_2,
                TIPO_2_UNIVERSIDAD, TIPO_2_OTRAS_INSTITUCIONES_2, TIPO_2_COMUNIDAD_AUTONOMA, TIPO_2_UNIVERSIDAD_2,
                TIPO_2_ORGANISMO_PUBLICO_3, TIPO_2_ENTIDAD_LOCAL, TIPO_2_OTRAS_INSTITUCIONES_3,
                TIPO_3_TODAS_LAS_ADMINISTRACIONES_PUBLICAS, TIPO_3_PROCEDIMIENTOS_Y_O_TRAMITES,
                TIPO_3_ADMINISTRACION_GENERAL_DEL_ESTADO, TIPO_3_ORGANISMO_PUBLICO_2, TIPO_3_UNIVERSIDAD_2,
                TIPO_3_OTRAS_INSTITUCIONES_2, TIPO_3_ORGANISMO_PUBLICO, TIPO_3_UNIVERSIDAD,
                TIPO_3_OTRAS_INSTITUCIONES_3, TIPO_3_ENTIDAD_LOCAL, TIPO_3_OTRAS_INSTITUCIONES };

        for (TipoApoderamiento ta : alltipos) {
            tipos.put(ta.tipo + "_" + ta.subtipo, ta);
        }

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
