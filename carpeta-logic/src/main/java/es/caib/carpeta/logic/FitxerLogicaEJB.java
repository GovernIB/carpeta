package es.caib.carpeta.logic;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

/*
import es.caib.carpeta.ejb.AnnexEJB;
import es.caib.carpeta.ejb.FitxerLocal;
import es.caib.carpeta.jpa.AnnexJPA;
import es.caib.carpeta.model.entity.AnnexFirmat;
import es.caib.carpeta.model.fields.AnnexFields;
import es.caib.carpeta.model.fields.AnnexFirmatFields;

import org.fundaciobit.genapp.common.i18n.I18NException;

*/

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
