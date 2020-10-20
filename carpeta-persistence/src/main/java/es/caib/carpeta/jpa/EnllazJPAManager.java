
package es.caib.carpeta.jpa;

import es.caib.carpeta.model.dao.IEnllazManager;
import es.caib.carpeta.model.entity.Enllaz;
import es.caib.carpeta.model.fields.EnllazFields;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.TableName;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class EnllazJPAManager
		 extends AbstractJPAManager<Enllaz, Long>
		 implements EnllazIJPAManager, IEnllazManager, EnllazFields {




  private static final long serialVersionUID = 411601652L;

	 public static final TableName<Enllaz> _TABLENAME =  new TableName<Enllaz>("EnllazJPA");


  @PersistenceContext
  protected EntityManager __em;

  public EnllazJPAManager() {
  }

  protected EnllazJPAManager(EntityManager __em) {
    this.__em = __em;
  }

  @Override
  public EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return EnllazJPA. class;
	}



	public TableName<Enllaz> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Enllaz[] listToArray(List<Enllaz> list)  {
		if(list == null) { return null; };
		return list.toArray(new Enllaz[list.size()]);
	};

	public synchronized Enllaz create( int _tipus_, long _nomID_, long _urlID_, long _entitatID_, long _logoID_) throws I18NException {
		EnllazJPA __bean =  new EnllazJPA(_tipus_,_nomID_,_urlID_,_entitatID_,_logoID_);
		return create(__bean);
	}



 public void delete(long _enllazID_) {
   delete(findByPrimaryKey(_enllazID_));
 }




	public Enllaz findByPrimaryKey(long _enllazID_) {
	  return __em.find(EnllazJPA.class, _enllazID_);  
	}
	@Override
	protected Enllaz getJPAInstance(Enllaz __bean) {
		return convertToJPA(__bean);
	}


	public static EnllazJPA convertToJPA(Enllaz __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof EnllazJPA) {
	    return (EnllazJPA)__bean;
	  }
	  
	  return EnllazJPA.toJPA(__bean);
	}

  @Override
  public Enllaz create(Enllaz transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.create(transientInstance);
  }


  @Override
  public Enllaz update(Enllaz transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.update(transientInstance);
  }


  private void processTranslations(Enllaz transientInstance) {
    if (transientInstance != null) {
      if (transientInstance.getNomID() == 0) {
        if (transientInstance instanceof EnllazJPA) {
          EnllazJPA _jpa = (EnllazJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getNom();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setNomID(_trad.getTraduccioID());
          }
        }
      }
      if (transientInstance.getUrlID() == 0) {
        if (transientInstance instanceof EnllazJPA) {
          EnllazJPA _jpa = (EnllazJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getUrl();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setUrlID(_trad.getTraduccioID());
          }
        }
      }
    }
  }


}