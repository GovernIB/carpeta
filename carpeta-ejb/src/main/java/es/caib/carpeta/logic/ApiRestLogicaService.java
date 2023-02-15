package es.caib.carpeta.logic;

import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.carpeta.model.entity.Entitat;
import es.caib.carpeta.model.entity.Estadistica;
import es.caib.carpeta.model.entity.NotificacioApp;
import es.caib.carpeta.model.entity.PluginEntitat;
import es.caib.carpeta.persistence.EstadisticaJPA;
import es.caib.carpeta.persistence.PluginJPA;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface ApiRestLogicaService {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/ApiRestLogicaEJB!es.caib.carpeta.logic.ApiRestLogicaService";

    public PluginJPA pluginEjbFindByPrimaryKey(Long pluginID);
    
    public List<NotificacioApp> notificacioLogicaEjbSelect(Where w) throws I18NException;

    public String ciutadaLogicaEjbExecuteQueryOne(StringField field, Where w) throws I18NException;
    
    public Entitat entitatLogicaEjbFindByPrimaryKey(Long entitatID);
    
    public List<PluginEntitat> pluginEntitatEjbSelect(Where w) throws I18NException;
    
    public Estadistica estadisticaEjbCreate(EstadisticaJPA est) throws I18NException;
    
    public String seccioEjbExecuteQueryOne(StringField field, Where w) throws I18NException;
}
