package es.caib.carpeta.logic;

import javax.annotation.security.PermitAll;


import javax.ejb.Stateless;

import es.caib.carpeta.ejb.FitxerEJB;
import es.caib.carpeta.jpa.FitxerJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "FitxerLogicaEJB")
public class FitxerLogicaEJB extends FitxerEJB implements FitxerLogicaLocal {

    @Override
    @PermitAll
    public FitxerJPA findByPrimaryKey(Long _ID_) {
        return (FitxerJPA) super.findByPrimaryKey(_ID_);
    }

    @Override
    @PermitAll
    public FitxerJPA findByPrimaryKey(long _ID_) {
        return (FitxerJPA) super.findByPrimaryKey(_ID_);
    }

}
