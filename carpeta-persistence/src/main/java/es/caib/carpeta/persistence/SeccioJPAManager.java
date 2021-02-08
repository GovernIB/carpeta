
package es.caib.carpeta.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.carpeta.model.entity.*;
import es.caib.carpeta.model.fields.*;
import es.caib.carpeta.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class SeccioJPAManager
		 extends AbstractJPAManager<Seccio, Long>
		 implements SeccioIJPAManager, ISeccioManager, SeccioFields {




  private static final long serialVersionUID = -903996310L;

	 public static final TableName<Seccio> _TABLENAME =  new TableName<Seccio>("SeccioJPA");


  @PersistenceContext
  protected EntityManager __em;

  public SeccioJPAManager() {
  }

  protected SeccioJPAManager(EntityManager __em) {
    this.__em = __em;
  }

  @Override
  public EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return SeccioJPA. class;
	}



	public TableName<Seccio> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Seccio[] listToArray(List<Seccio> list)  {
		if(list == null) { return null; };
		return list.toArray(new Seccio[list.size()]);
	};

	public synchronized Seccio create( long _nomID_, long _descripcioID_, boolean _activa_, long _iconaID_, java.lang.Long _seccioPareID_, long _entitatID_) throws I18NException {
		SeccioJPA __bean =  new SeccioJPA(_nomID_,_descripcioID_,_activa_,_iconaID_,_seccioPareID_,_entitatID_);
		return create(__bean);
	}



 public void delete(long _seccioID_) {
   delete(findByPrimaryKey(_seccioID_));
 }




	public Seccio findByPrimaryKey(long _seccioID_) {
	  return __em.find(SeccioJPA.class, _seccioID_);  
	}
	@Override
	protected Seccio getJPAInstance(Seccio __bean) {
		return convertToJPA(__bean);
	}


	public static SeccioJPA convertToJPA(Seccio __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof SeccioJPA) {
	    return (SeccioJPA)__bean;
	  }
	  
	  return SeccioJPA.toJPA(__bean);
	}

  @Override
  public Seccio create(Seccio transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.create(transientInstance);
  }


  @Override
  public Seccio update(Seccio transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.update(transientInstance);
  }


  private void processTranslations(Seccio transientInstance) {
    if (transientInstance != null) {
      if (transientInstance.getNomID() == 0) {
        if (transientInstance instanceof SeccioJPA) {
          SeccioJPA _jpa = (SeccioJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getNom();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setNomID(_trad.getTraduccioID());
          }
        }
      }
      if (transientInstance.getDescripcioID() == 0) {
        if (transientInstance instanceof SeccioJPA) {
          SeccioJPA _jpa = (SeccioJPA)transientInstance;
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