package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import es.caib.carpeta.ejb.CiutadaEJB;
import es.caib.carpeta.model.entity.Ciutada;

/**
 * 
 * @author anadal
 *
 */
@Stateless
public class CiutadaLogicaEJB extends CiutadaEJB implements CiutadaLogicaService {

    @Override
    @PermitAll
    public Ciutada createPublic(Ciutada ciutada) throws I18NException {
        return super.create(ciutada);
    }

    @Override
    @PermitAll
    public void updatePublic(Ciutada ciutada) throws I18NException {
        super.update(ciutada);
    }

}
