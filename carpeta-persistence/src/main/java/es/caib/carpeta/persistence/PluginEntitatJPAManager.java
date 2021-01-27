
package es.caib.carpeta.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.carpeta.model.entity.*;
import es.caib.carpeta.model.fields.*;
import es.caib.carpeta.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class PluginEntitatJPAManager
		 extends AbstractJPAManager<PluginEntitat, Long>
		 implements PluginEntitatIJPAManager, IPluginEntitatManager, PluginEntitatFields {




  private static final long serialVersionUID = 410411816L;

	 public static final TableName<PluginEntitat> _TABLENAME =  new TableName<PluginEntitat>("PluginEntitatJPA");


  @PersistenceContext
  protected EntityManager __em;

  public PluginEntitatJPAManager() {
  }

  protected PluginEntitatJPAManager(EntityManager __em) {
    this.__em = __em;
  }

  @Override
  public EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return PluginEntitatJPA. class;
	}



	public TableName<PluginEntitat> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public PluginEntitat[] listToArray(List<PluginEntitat> list)  {
		if(list == null) { return null; };
		return list.toArray(new PluginEntitat[list.size()]);
	};

	public synchronized PluginEntitat create( long _pluginID_, long _entitatID_, boolean _actiu_) throws I18NException {
		PluginEntitatJPA __bean =  new PluginEntitatJPA(_pluginID_,_entitatID_,_actiu_);
		return create(__bean);
	}



 public void delete(long _pluginEntitatID_) {
   delete(findByPrimaryKey(_pluginEntitatID_));
 }




	public PluginEntitat findByPrimaryKey(long _pluginEntitatID_) {
	  return __em.find(PluginEntitatJPA.class, _pluginEntitatID_);  
	}
	@Override
	protected PluginEntitat getJPAInstance(PluginEntitat __bean) {
		return convertToJPA(__bean);
	}


	public static PluginEntitatJPA convertToJPA(PluginEntitat __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof PluginEntitatJPA) {
	    return (PluginEntitatJPA)__bean;
	  }
	  
	  return PluginEntitatJPA.toJPA(__bean);
	}


}