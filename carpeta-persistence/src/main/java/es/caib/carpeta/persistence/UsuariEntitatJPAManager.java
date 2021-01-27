
package es.caib.carpeta.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.carpeta.model.entity.*;
import es.caib.carpeta.model.fields.*;
import es.caib.carpeta.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class UsuariEntitatJPAManager
		 extends AbstractJPAManager<UsuariEntitat, Long>
		 implements UsuariEntitatIJPAManager, IUsuariEntitatManager, UsuariEntitatFields {




  private static final long serialVersionUID = 795835066L;

	 public static final TableName<UsuariEntitat> _TABLENAME =  new TableName<UsuariEntitat>("UsuariEntitatJPA");


  @PersistenceContext
  protected EntityManager __em;

  public UsuariEntitatJPAManager() {
  }

  protected UsuariEntitatJPAManager(EntityManager __em) {
    this.__em = __em;
  }

  @Override
  public EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return UsuariEntitatJPA. class;
	}



	public TableName<UsuariEntitat> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public UsuariEntitat[] listToArray(List<UsuariEntitat> list)  {
		if(list == null) { return null; };
		return list.toArray(new UsuariEntitat[list.size()]);
	};

	public synchronized UsuariEntitat create( long _usuariID_, long _entitatID_, boolean _actiu_) throws I18NException {
		UsuariEntitatJPA __bean =  new UsuariEntitatJPA(_usuariID_,_entitatID_,_actiu_);
		return create(__bean);
	}



 public void delete(long _usuariEntitatID_) {
   delete(findByPrimaryKey(_usuariEntitatID_));
 }




	public UsuariEntitat findByPrimaryKey(long _usuariEntitatID_) {
	  return __em.find(UsuariEntitatJPA.class, _usuariEntitatID_);  
	}
	@Override
	protected UsuariEntitat getJPAInstance(UsuariEntitat __bean) {
		return convertToJPA(__bean);
	}


	public static UsuariEntitatJPA convertToJPA(UsuariEntitat __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof UsuariEntitatJPA) {
	    return (UsuariEntitatJPA)__bean;
	  }
	  
	  return UsuariEntitatJPA.toJPA(__bean);
	}


}