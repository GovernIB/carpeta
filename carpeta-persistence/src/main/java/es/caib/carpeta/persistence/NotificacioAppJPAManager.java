
package es.caib.carpeta.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.carpeta.model.entity.*;
import es.caib.carpeta.model.fields.*;
import es.caib.carpeta.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class NotificacioAppJPAManager
         extends AbstractJPAManager<NotificacioApp, Long>
         implements NotificacioAppIJPAManager, INotificacioAppManager, NotificacioAppFields {




    private static final long serialVersionUID = 1272824193L;

    public static final TableName<NotificacioApp> _TABLENAME =  new TableName<NotificacioApp>("NotificacioAppJPA");


    @PersistenceContext
    protected EntityManager __em;

    public NotificacioAppJPAManager() {
    }

    protected NotificacioAppJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return NotificacioAppJPA. class;
    }



    public TableName<NotificacioApp> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public NotificacioApp[] listToArray(List<NotificacioApp> list)  {
        if(list == null) { return null; };
        return list.toArray(new NotificacioApp[list.size()]);
    };

    public synchronized NotificacioApp create( java.lang.String _codi_, long _titolID_, long _missatgeID_, java.lang.Long _frontPluginID_, java.lang.String _ajuda_, boolean _activa_) throws I18NException {
        NotificacioAppJPA __bean =  new NotificacioAppJPA(_codi_,_titolID_,_missatgeID_,_frontPluginID_,_ajuda_,_activa_);
        return create(__bean);
    }



 public void delete(long _notificacioAppID_) {
   delete(findByPrimaryKey(_notificacioAppID_));
 }




    public NotificacioApp findByPrimaryKey(long _notificacioAppID_) {
        return __em.find(NotificacioAppJPA.class, _notificacioAppID_);  
    }
    @Override
    protected NotificacioApp getJPAInstance(NotificacioApp __bean) {
        return convertToJPA(__bean);
    }


    public static NotificacioAppJPA convertToJPA(NotificacioApp __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof NotificacioAppJPA) {
        return (NotificacioAppJPA)__bean;
      }
      
      return NotificacioAppJPA.toJPA(__bean);
    }

  @Override
  public NotificacioApp create(NotificacioApp transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.create(transientInstance);
  }


  @Override
  public NotificacioApp update(NotificacioApp transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.update(transientInstance);
  }


  private void processTranslations(NotificacioApp transientInstance) {
    if (transientInstance != null) {
      if (transientInstance.getTitolID() == 0) {
        if (transientInstance instanceof NotificacioAppJPA) {
          NotificacioAppJPA _jpa = (NotificacioAppJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getTitol();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setTitolID(_trad.getTraduccioID());
          }
        }
      }
      if (transientInstance.getMissatgeID() == 0) {
        if (transientInstance instanceof NotificacioAppJPA) {
          NotificacioAppJPA _jpa = (NotificacioAppJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getMissatge();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setMissatgeID(_trad.getTraduccioID());
          }
        }
      }
    }
  }


}