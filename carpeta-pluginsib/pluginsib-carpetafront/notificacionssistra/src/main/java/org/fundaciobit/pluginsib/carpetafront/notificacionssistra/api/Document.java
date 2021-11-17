package org.fundaciobit.pluginsib.carpetafront.notificacionssistra.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Document {

    private String nom;
    private String mediaType;
    private Long mida;
    private String url;
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getMediaType() {
        return mediaType;
    }
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
    public Long getMida() {
        return mida;
    }
    public void setMida(Long mida) {
        this.mida = mida;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    
}