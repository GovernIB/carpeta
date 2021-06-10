package org.fundaciobit.pluginsib.carpetafront.notib.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Arxiu {

    String nom;
    String mediaType;
    String contingut;
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
    public String getContingut() {
        return contingut;
    }
    public void setContingut(String contingut) {
        this.contingut = contingut;
    }
    
}
