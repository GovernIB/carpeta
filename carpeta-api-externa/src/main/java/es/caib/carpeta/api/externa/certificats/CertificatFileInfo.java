package es.caib.carpeta.api.externa.certificats;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author fbosch
 *
 */
@Schema(name = "CertificatFileInfo")
public class CertificatFileInfo {

    protected String nom;
    protected String mime;
    protected int length;
    
    protected String dataB64;
    
    //@Schema(name = "bytes",  required = true, type = "string", format = "byte")
    //private byte[] bytes = null;


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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDataB64() {
        return dataB64;
    }

    public void setDataB64(String dataB64) {
        this.dataB64 = dataB64;
    }
    
    
/*
    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
    */
   
}
