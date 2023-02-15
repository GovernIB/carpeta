package es.caib.carpeta.logic;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.model.entity.Entitat;
import es.caib.carpeta.model.entity.Estadistica;
import es.caib.carpeta.model.entity.NotificacioApp;
import es.caib.carpeta.model.entity.PluginEntitat;
import es.caib.carpeta.model.fields.SeccioFields;
import es.caib.carpeta.persistence.EstadisticaJPA;
import es.caib.carpeta.persistence.PluginJPA;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.Where;

/**
 * 
 * @author anadal
 * 
 */
@RunAs(Constants.CAR_ADMIN)
@Stateless
public class ApiRestLogicaEJB implements ApiRestLogicaService {

    @EJB(mappedName = CiutadaLogicaService.JNDI_NAME)
    protected CiutadaLogicaService ciutadaLogicaEjb;

    @EJB(mappedName = NotificacioAppLogicaService.JNDI_NAME)
    protected NotificacioAppLogicaService notificacioLogicaEjb;

    @EJB(mappedName = es.caib.carpeta.ejb.PluginService.JNDI_NAME)
    protected es.caib.carpeta.ejb.PluginService pluginEjb;

    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
    protected EntitatLogicaService entitatLogicaEjb;

    @EJB(mappedName = PluginEntitatLogicaService.JNDI_NAME)
    protected PluginEntitatLogicaService pluginEntitatEjb;

    @EJB(mappedName = es.caib.carpeta.ejb.EstadisticaService.JNDI_NAME)
    protected es.caib.carpeta.ejb.EstadisticaService estadisticaEjb;

    @EJB(mappedName = es.caib.carpeta.ejb.SeccioService.JNDI_NAME)
    protected es.caib.carpeta.ejb.SeccioService seccioEjb;

    @Override
    public PluginJPA pluginEjbFindByPrimaryKey(Long pluginID) {
        return pluginEjb.findByPrimaryKey(pluginID);
    }

    @Override
    public List<NotificacioApp> notificacioLogicaEjbSelect(Where w) throws I18NException {
        return notificacioLogicaEjb.select(w);
    }

    @Override
    public String ciutadaLogicaEjbExecuteQueryOne(StringField field, Where w) throws I18NException {
        return ciutadaLogicaEjb.executeQueryOne(field, w);
    }

    @Override
    public Entitat entitatLogicaEjbFindByPrimaryKey(Long entitatID) {
        return entitatLogicaEjb.findByPrimaryKey(entitatID);
    }

    @Override
    public List<PluginEntitat> pluginEntitatEjbSelect(Where w) throws I18NException {
        return pluginEntitatEjb.select(w);
    }

    @Override
    public Estadistica estadisticaEjbCreate(EstadisticaJPA est) throws I18NException {
        return estadisticaEjb.create(est);
    }

    @Override
    public String seccioEjbExecuteQueryOne(StringField field, Where w) throws I18NException {
        return seccioEjb.executeQueryOne(field, w);
    }
}
