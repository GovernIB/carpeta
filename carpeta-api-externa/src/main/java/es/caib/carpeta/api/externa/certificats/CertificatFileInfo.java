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
    @Schema(name = "bytes",  required = true, type = "string", format = "binary")
    private byte[] bytes = null;

    public CertificatFileInfo() {
        
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
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
    
    
   
    }

    
    
    



