package org.fundaciobit.pluginsib.carpetafront.notib.api;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Persona {

    private PersonaTipus tipus;
    private String nom;
    private String llinatge1;
    private String llinatge2;
    private String nif;
    private String email;

    public PersonaTipus getTipus() {
        return tipus;
    }

    public void setTipus(PersonaTipus tipus) {
        this.tipus = tipus;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLlinatge1() {
        return llinatge1;
    }

    public void setLlinatge1(String llinatge1) {
        this.llinatge1 = llinatge1;
    }

    public String getLlinatge2() {
        return llinatge2;
    }

    public void setLlinatge2(String llinatge2) {
        this.llinatge2 = llinatge2;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
