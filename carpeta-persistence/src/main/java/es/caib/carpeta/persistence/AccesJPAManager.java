
package es.caib.carpeta.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.carpeta.model.entity.*;
import es.caib.carpeta.model.fields.*;
import es.caib.carpeta.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class AccesJPAManager
         extends AbstractJPAManager<Acces, Long>
         implements AccesIJPAManager, IAccesManager, AccesFields {




    private static final long serialVersionUID = 969175425L;

    public static final TableName<Acces> _TABLENAME =  new TableName<Acces>("AccesJPA");


    @PersistenceContext
    protected EntityManager __em;

    public AccesJPAManager() {
    }

    protected AccesJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return AccesJPA. class;
    }



    public TableName<Acces> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Acces[] listToArray(List<Acces> list)  {
        if(list == null) { return null; };
        return list.toArray(new Acces[list.size()]);
    };

    public synchronized Acces create( int _tipus_, java.lang.String _nom_, java.lang.String _llinatges_, java.lang.String _nif_, java.lang.String _ip_, java.lang.String _proveidorIdentitat_, java.lang.String _metodeAutenticacio_, java.lang.Integer _qaa_, java.sql.Timestamp _dataAcces_, java.lang.Long _pluginID_, long _entitatID_, java.lang.String _idioma_, boolean _resultat_, java.lang.String _idsessio_) throws I18NException {
        AccesJPA __bean =  new AccesJPA(_tipus_,_nom_,_llinatges_,_nif_,_ip_,_proveidorIdentitat_,_metodeAutenticacio_,_qaa_,_dataAcces_,_pluginID_,_entitatID_,_idioma_,_resultat_,_idsessio_);
        return create(__bean);
    }



 public void delete(long _accesID_) {
   delete(findByPrimaryKey(_accesID_));
 }




    public Acces findByPrimaryKey(long _accesID_) {
        return __em.find(AccesJPA.class, _accesID_);  
    }
    @Override
    protected Acces getJPAInstance(Acces __bean) {
        return convertToJPA(__bean);
    }


    public static AccesJPA convertToJPA(Acces __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof AccesJPA) {
        return (AccesJPA)__bean;
      }
      
      return AccesJPA.toJPA(__bean);
    }


}