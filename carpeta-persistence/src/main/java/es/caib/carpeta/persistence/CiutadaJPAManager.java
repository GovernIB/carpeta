
package es.caib.carpeta.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.carpeta.model.entity.*;
import es.caib.carpeta.model.fields.*;
import es.caib.carpeta.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class CiutadaJPAManager
         extends AbstractJPAManager<Ciutada, Long>
         implements CiutadaIJPAManager, ICiutadaManager, CiutadaFields {




    private static final long serialVersionUID = -1098039029L;

    public static final TableName<Ciutada> _TABLENAME =  new TableName<Ciutada>("CiutadaJPA");


    @PersistenceContext
    protected EntityManager __em;

    public CiutadaJPAManager() {
    }

    protected CiutadaJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return CiutadaJPA. class;
    }



    public TableName<Ciutada> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Ciutada[] listToArray(List<Ciutada> list)  {
        if(list == null) { return null; };
        return list.toArray(new Ciutada[list.size()]);
    };

    public synchronized Ciutada create( java.lang.String _nif_, java.lang.String _llinatge1_, java.lang.String _llinatge2_, java.lang.String _nom_, boolean _empresa_, java.lang.String _representantNif_, java.lang.String _representantLlinatge1_, java.lang.String _representantLlinatge2_, java.lang.String _representantNom_, java.sql.Timestamp _dataCreacio_, java.lang.String _mobileId_) throws I18NException {
        CiutadaJPA __bean =  new CiutadaJPA(_nif_,_llinatge1_,_llinatge2_,_nom_,_empresa_,_representantNif_,_representantLlinatge1_,_representantLlinatge2_,_representantNom_,_dataCreacio_,_mobileId_);
        return create(__bean);
    }



 public void delete(long _ciutadaID_) {
   delete(findByPrimaryKey(_ciutadaID_));
 }




    public Ciutada findByPrimaryKey(long _ciutadaID_) {
        return __em.find(CiutadaJPA.class, _ciutadaID_);  
    }
    @Override
    protected Ciutada getJPAInstance(Ciutada __bean) {
        return convertToJPA(__bean);
    }


    public static CiutadaJPA convertToJPA(Ciutada __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof CiutadaJPA) {
        return (CiutadaJPA)__bean;
      }
      
      return CiutadaJPA.toJPA(__bean);
    }


}