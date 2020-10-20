
package es.caib.carpeta.jpa;

import es.caib.carpeta.model.dao.IPropietatGlobalManager;
import es.caib.carpeta.model.entity.PropietatGlobal;
import es.caib.carpeta.model.fields.PropietatGlobalFields;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.TableName;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class PropietatGlobalJPAManager
		 extends AbstractJPAManager<PropietatGlobal, Long>
		 implements PropietatGlobalIJPAManager, IPropietatGlobalManager, PropietatGlobalFields {




  private static final long serialVersionUID = -1396349219L;

	 public static final TableName<PropietatGlobal> _TABLENAME =  new TableName<PropietatGlobal>("PropietatGlobalJPA");


  @PersistenceContext
  protected EntityManager __em;

  public PropietatGlobalJPAManager() {
  }

  protected PropietatGlobalJPAManager(EntityManager __em) {
    this.__em = __em;
  }

  @Override
  public EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return PropietatGlobalJPA. class;
	}



	public TableName<PropietatGlobal> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public PropietatGlobal[] listToArray(List<PropietatGlobal> list)  {
		if(list == null) { return null; };
		return list.toArray(new PropietatGlobal[list.size()]);
	};

	public synchronized PropietatGlobal create( java.lang.String _codi_, java.lang.String _value_, java.lang.String _descripcio_, java.lang.Long _entitatID_) throws I18NException {
		PropietatGlobalJPA __bean =  new PropietatGlobalJPA(_codi_,_value_,_descripcio_,_entitatID_);
		return create(__bean);
	}



 public void delete(long _propietaGlobalID_) {
   delete(findByPrimaryKey(_propietaGlobalID_));
 }




	public PropietatGlobal findByPrimaryKey(long _propietaGlobalID_) {
	  return __em.find(PropietatGlobalJPA.class, _propietaGlobalID_);  
	}
	@Override
	protected PropietatGlobal getJPAInstance(PropietatGlobal __bean) {
		return convertToJPA(__bean);
	}


	public static PropietatGlobalJPA convertToJPA(PropietatGlobal __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof PropietatGlobalJPA) {
	    return (PropietatGlobalJPA)__bean;
	  }
	  
	  return PropietatGlobalJPA.toJPA(__bean);
	}


}