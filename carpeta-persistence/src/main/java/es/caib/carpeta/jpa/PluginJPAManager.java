
package es.caib.carpeta.jpa;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.carpeta.model.entity.*;
import es.caib.carpeta.model.fields.*;
import es.caib.carpeta.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class PluginJPAManager
		 extends AbstractJPAManager<Plugin, Long>
		 implements PluginIJPAManager, IPluginManager, PluginFields {




  private static final long serialVersionUID = -1591214331L;

	 public static final TableName<Plugin> _TABLENAME =  new TableName<Plugin>("PluginJPA");


  @PersistenceContext
  protected EntityManager __em;

  public PluginJPAManager() {
  }

  protected PluginJPAManager(EntityManager __em) {
    this.__em = __em;
  }

  @Override
  public EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return PluginJPA. class;
	}



	public TableName<Plugin> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Plugin[] listToArray(List<Plugin> list)  {
		if(list == null) { return null; };
		return list.toArray(new Plugin[list.size()]);
	};

	public synchronized Plugin create( long _nomID_, java.lang.String _classe_, java.lang.String _propietats_, boolean _actiu_, int _tipus_) throws I18NException {
		PluginJPA __bean =  new PluginJPA(_nomID_,_classe_,_propietats_,_actiu_,_tipus_);
		return create(__bean);
	}



 public void delete(long _pluginID_) {
   delete(findByPrimaryKey(_pluginID_));
 }




	public Plugin findByPrimaryKey(long _pluginID_) {
	  return __em.find(PluginJPA.class, _pluginID_);  
	}
	@Override
	protected Plugin getJPAInstance(Plugin __bean) {
		return convertToJPA(__bean);
	}


	public static PluginJPA convertToJPA(Plugin __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof PluginJPA) {
	    return (PluginJPA)__bean;
	  }
	  
	  return PluginJPA.toJPA(__bean);
	}

  @Override
  public Plugin create(Plugin transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.create(transientInstance);
  }


  @Override
  public Plugin update(Plugin transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.update(transientInstance);
  }


  private void processTranslations(Plugin transientInstance) {
    if (transientInstance != null) {
      if (transientInstance.getNomID() == 0) {
        if (transientInstance instanceof PluginJPA) {
          PluginJPA _jpa = (PluginJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getNom();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setNomID(_trad.getTraduccioID());
          }
        }
      }
    }
  }


}