
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Idioma;
import es.caib.carpeta.persistence.IdiomaJPA;
import es.caib.carpeta.persistence.IdiomaJPAManager;

import es.caib.carpeta.commons.utils.Constants;

@Stateless
public class IdiomaEJB extends IdiomaJPAManager implements IdiomaService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(Idioma instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Idioma create(Idioma instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Idioma update(Idioma instance) throws I18NException {
         return super.update(instance);
    }

    public void deleteIncludingFiles(Idioma instance, es.caib.carpeta.ejb.FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public IdiomaJPA findByPrimaryKey(String _ID_) {
        return (IdiomaJPA)super.findByPrimaryKey(_ID_);
    }

}
