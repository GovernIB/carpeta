
package es.caib.carpeta.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.carpeta.model.entity.*;
import es.caib.carpeta.model.fields.*;
import es.caib.carpeta.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class PreguntesFrequentsJPAManager
         extends AbstractJPAManager<PreguntesFrequents, Long>
         implements PreguntesFrequentsIJPAManager, IPreguntesFrequentsManager, PreguntesFrequentsFields {




    private static final long serialVersionUID = -715920078L;

    public static final TableName<PreguntesFrequents> _TABLENAME =  new TableName<PreguntesFrequents>("PreguntesFrequentsJPA");


    @PersistenceContext
    protected EntityManager __em;

    public PreguntesFrequentsJPAManager() {
    }

    protected PreguntesFrequentsJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return PreguntesFrequentsJPA. class;
    }



    public TableName<PreguntesFrequents> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public PreguntesFrequents[] listToArray(List<PreguntesFrequents> list)  {
        if(list == null) { return null; };
        return list.toArray(new PreguntesFrequents[list.size()]);
    };

    public synchronized PreguntesFrequents create( long _enunciatID_, long _respostaID_, int _ordre_, long _entitatID_, java.lang.Long _fitxer1ID_, java.lang.Long _fitxer2ID_, java.lang.Long _fitxer3ID_) throws I18NException {
        PreguntesFrequentsJPA __bean =  new PreguntesFrequentsJPA(_enunciatID_,_respostaID_,_ordre_,_entitatID_,_fitxer1ID_,_fitxer2ID_,_fitxer3ID_);
        return create(__bean);
    }



 public void delete(long _preguntesFrequentsID_) {
   delete(findByPrimaryKey(_preguntesFrequentsID_));
 }




    public PreguntesFrequents findByPrimaryKey(long _preguntesFrequentsID_) {
        return __em.find(PreguntesFrequentsJPA.class, _preguntesFrequentsID_);  
    }
    @Override
    protected PreguntesFrequents getJPAInstance(PreguntesFrequents __bean) {
        return convertToJPA(__bean);
    }


    public static PreguntesFrequentsJPA convertToJPA(PreguntesFrequents __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof PreguntesFrequentsJPA) {
        return (PreguntesFrequentsJPA)__bean;
      }
      
      return PreguntesFrequentsJPA.toJPA(__bean);
    }

  @Override
  public PreguntesFrequents create(PreguntesFrequents transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.create(transientInstance);
  }


  @Override
  public PreguntesFrequents update(PreguntesFrequents transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.update(transientInstance);
  }


  private void processTranslations(PreguntesFrequents transientInstance) {
    if (transientInstance != null) {
      if (transientInstance.getEnunciatID() == 0) {
        if (transientInstance instanceof PreguntesFrequentsJPA) {
          PreguntesFrequentsJPA _jpa = (PreguntesFrequentsJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getEnunciat();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setEnunciatID(_trad.getTraduccioID());
          }
        }
      }
      if (transientInstance.getRespostaID() == 0) {
        if (transientInstance instanceof PreguntesFrequentsJPA) {
          PreguntesFrequentsJPA _jpa = (PreguntesFrequentsJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getResposta();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setRespostaID(_trad.getTraduccioID());
          }
        }
      }
    }
  }


}