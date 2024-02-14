package es.caib.carpeta.api.externa.estadistiques;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * Dades d'un accés
 * 
 * @author jagarcia
 * @author anadal
 *
 */
@Schema(name = "Acces")
public class AccesDTO {

    private String proveidor;
    private String metodeAutenticacio;
    private java.lang.Integer qaa;
    @JsonProperty("data")
    @Schema(
            description = "Data en que es va fer l'accés",
            type = "string",
            format = "date-time",
            pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private java.sql.Timestamp data;
    private String idioma;
    private String entitat;
    private String tipus;
    private String plugin;
    private String idSessio;

    public AccesDTO() {
    }

    public AccesDTO(String proveidor, String metodeAutenticacio, java.lang.Integer qaa, java.sql.Timestamp data,
            String idioma, String entitat, String tipus, String plugin, String idSessio) {
        this.proveidor = proveidor;
        this.metodeAutenticacio = metodeAutenticacio;
        this.qaa = qaa;
        this.data = data;
        this.idioma = idioma;
        this.entitat = entitat;
        this.tipus = tipus;
        this.plugin = plugin;
        this.idSessio = idSessio;
    }

    public String getProveidor() {
        return proveidor;
    }

    public void setProveidor(String proveidor) {
        this.proveidor = proveidor;
    }

    public String getMetodeAutenticacio() {
        return metodeAutenticacio;
    }

    public void setMetodeAutenticacio(String metodeAutenticacio) {
        this.metodeAutenticacio = metodeAutenticacio;
    }

    public java.lang.Integer getQaa() {
        return qaa;
    }

    public void setQaa(java.lang.Integer qaa) {
        this.qaa = qaa;
    }

    public java.sql.Timestamp getData() {
        return data;
    }

    public void setData(java.sql.Timestamp data) {
        this.data = data;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getEntitat() {
        return entitat;
    }

    public void setEntitat(String entitat) {
        this.entitat = entitat;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public String getIdSessio() {
        return idSessio;
    }

    public void setIdSessio(String idSessio) {
        this.idSessio = idSessio;
    }

    @Override
    public String toString() {
        return "AccesDTO{" + "proveidor=" + proveidor + ", metodeAutenticacio=" + metodeAutenticacio + ", qaa=" + qaa
                + ", data=" + data + ", idioma=" + idioma + ", entitat=" + entitat + ", tipus=" + tipus + ", plugin="
                + plugin + ", idSessio=" + idSessio + "}";
    }

}
