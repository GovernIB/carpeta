package org.fundaciobit.pluginsib.carpetafront.notib.api;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Resposta {

    int numeroElementsTotals;
    int numeroElementsRetornats;
    List<Transmissio> resultat;

    public int getNumeroElementsTotals() {
        return numeroElementsTotals;
    }

    public void setNumeroElementsTotals(int numeroElementsTotals) {
        this.numeroElementsTotals = numeroElementsTotals;
    }

    public int getNumeroElementsRetornats() {
        return numeroElementsRetornats;
    }

    public void setNumeroElementsRetornats(int numeroElementsRetornats) {
        this.numeroElementsRetornats = numeroElementsRetornats;
    }

    public List<Transmissio> getResultat() {
        return resultat;
    }

    public void setResultat(List<Transmissio> resultat) {
        this.resultat = resultat;
    }

}