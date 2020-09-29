package es.caib.carpeta.pluginsib.carpetafront.api;

public class FileInfo {

    private String nom;
    private String mime;
    private byte[] contingut;

    public FileInfo() {
    }

    public FileInfo(String nom, String mime, byte[] contingut) {
        this.nom = nom;
        this.mime = mime;
        this.contingut = contingut;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public byte[] getContingut() {
        return contingut;
    }

    public void setContingut(byte[] contingut) {
        this.contingut = contingut;
    }
}
