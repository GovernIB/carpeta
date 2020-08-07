
package es.caib.carpeta.jpa;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.carpeta.model.entity.*;
import es.caib.carpeta.model.fields.*;
import es.caib.carpeta.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class AvisJPAManager
		 extends AbstractJPAManager<Avis, Long>
		 implements AvisIJPAManager, IAvisManager, AvisFields {




  private static final long serialVersionUID = 1008320753L;

	 public static final TableName<Avis> _TABLENAME =  new TableName<Avis>("AvisJPA");


  @PersistenceContext
  protected EntityManager __em;

  public AvisJPAManager() {
  }

  protected AvisJPAManager(EntityManager __em) {
    this.__em = __em;
  }

  @Override
  public EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return AvisJPA. class;
	}



	public TableName<Avis> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Avis[] listToArray(List<Avis> list)  {
		if(list == null) { return null; };
		return list.toArray(new Avis[list.size()]);
	};

	public synchronized Avis create( long _descripcioID_, long _entitatID_, java.sql.Timestamp _dataInici_, java.sql.Timestamp _dataFi_, int _tipus_) throws I18NException {
		AvisJPA __bean =  new AvisJPA(_descripcioID_,_entitatID_,_dataInici_,_dataFi_,_tipus_);
		return create(__bean);
	}



 public void delete(long _avisID_) {
   delete(findByPrimaryKey(_avisID_));
 }




	public Avis findByPrimaryKey(long _avisID_) {
	  return __em.find(AvisJPA.class, _avisID_);  
	}
	@Override
	protected Avis getJPAInstance(Avis __bean) {
		return convertToJPA(__bean);
	}


	public static AvisJPA convertToJPA(Avis __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof AvisJPA) {
	    return (AvisJPA)__bean;
	  }
	  
	  return AvisJPA.toJPA(__bean);
	}

  @Override
  public Avis create(Avis transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.create(transientInstance);
  }


  @Override
  public Avis update(Avis transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.update(transientInstance);
  }


  private void processTranslations(Avis transientInstance) {
    if (transientInstance != null) {
      if (transientInstance.getDescripcioID() == 0) {
        if (transientInstance instanceof AvisJPA) {
          AvisJPA _jpa = (AvisJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getDescripcio();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setDescripcioID(_trad.getTraduccioID());
          }
        }
      }
    }
  }


}