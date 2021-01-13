
package es.caib.carpeta.jpa;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.carpeta.model.entity.*;
import es.caib.carpeta.model.fields.*;
import es.caib.carpeta.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class AuditoriaJPAManager
		 extends AbstractJPAManager<Auditoria, Long>
		 implements AuditoriaIJPAManager, IAuditoriaManager, AuditoriaFields {




  private static final long serialVersionUID = 1834834024L;

	 public static final TableName<Auditoria> _TABLENAME =  new TableName<Auditoria>("AuditoriaJPA");


  @PersistenceContext
  protected EntityManager __em;

  public AuditoriaJPAManager() {
  }

  protected AuditoriaJPAManager(EntityManager __em) {
    this.__em = __em;
  }

  @Override
  public EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return AuditoriaJPA. class;
	}



	public TableName<Auditoria> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Auditoria[] listToArray(List<Auditoria> list)  {
		if(list == null) { return null; };
		return list.toArray(new Auditoria[list.size()]);
	};

	public synchronized Auditoria create( java.sql.Timestamp _dataAudit_, int _tipus_, java.lang.String _username_, java.lang.Long _entitatID_) throws I18NException {
		AuditoriaJPA __bean =  new AuditoriaJPA(_dataAudit_,_tipus_,_username_,_entitatID_);
		return create(__bean);
	}



 public void delete(long _auditoriaID_) {
   delete(findByPrimaryKey(_auditoriaID_));
 }




	public Auditoria findByPrimaryKey(long _auditoriaID_) {
	  return __em.find(AuditoriaJPA.class, _auditoriaID_);  
	}
	@Override
	protected Auditoria getJPAInstance(Auditoria __bean) {
		return convertToJPA(__bean);
	}


	public static AuditoriaJPA convertToJPA(Auditoria __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof AuditoriaJPA) {
	    return (AuditoriaJPA)__bean;
	  }
	  
	  return AuditoriaJPA.toJPA(__bean);
	}


}