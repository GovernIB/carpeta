
package es.caib.carpeta.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.carpeta.model.entity.*;
import es.caib.carpeta.model.fields.*;
import es.caib.carpeta.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class EstadisticaJPAManager
         extends AbstractJPAManager<Estadistica, Long>
         implements EstadisticaIJPAManager, IEstadisticaManager, EstadisticaFields {




    private static final long serialVersionUID = -57996552L;

    public static final TableName<Estadistica> _TABLENAME =  new TableName<Estadistica>("EstadisticaJPA");


    @PersistenceContext
    protected EntityManager __em;

    public EstadisticaJPAManager() {
    }

    protected EstadisticaJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return EstadisticaJPA. class;
    }



    public TableName<Estadistica> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Estadistica[] listToArray(List<Estadistica> list)  {
        if(list == null) { return null; };
        return list.toArray(new Estadistica[list.size()]);
    };

    public synchronized Estadistica create( int _tipus_, java.sql.Timestamp _dataEstadistica_, int _comptador_, java.lang.Long _pluginID_, java.lang.Long _entitatID_) throws I18NException {
        EstadisticaJPA __bean =  new EstadisticaJPA(_tipus_,_dataEstadistica_,_comptador_,_pluginID_,_entitatID_);
        return create(__bean);
    }



 public void delete(long _estadisticaID_) {
   delete(findByPrimaryKey(_estadisticaID_));
 }




    public Estadistica findByPrimaryKey(long _estadisticaID_) {
        return __em.find(EstadisticaJPA.class, _estadisticaID_);  
    }
    @Override
    protected Estadistica getJPAInstance(Estadistica __bean) {
        return convertToJPA(__bean);
    }


    public static EstadisticaJPA convertToJPA(Estadistica __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof EstadisticaJPA) {
        return (EstadisticaJPA)__bean;
      }
      
      return EstadisticaJPA.toJPA(__bean);
    }


}