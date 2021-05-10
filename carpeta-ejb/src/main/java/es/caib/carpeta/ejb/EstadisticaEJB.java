
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Estadistica;
import es.caib.carpeta.persistence.EstadisticaJPA;
import es.caib.carpeta.persistence.EstadisticaJPAManager;

import es.caib.carpeta.commons.utils.Constants;

@Stateless
public class EstadisticaEJB extends EstadisticaJPAManager implements EstadisticaService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(Estadistica instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Estadistica create(Estadistica instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Estadistica update(Estadistica instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public EstadisticaJPA findByPrimaryKey(Long _ID_) {
        return (EstadisticaJPA)super.findByPrimaryKey(_ID_);
    }

}
