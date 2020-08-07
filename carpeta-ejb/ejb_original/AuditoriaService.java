package es.caib.carpeta.ejb;

import es.caib.carpeta.persistence.Auditoria;
import es.caib.carpeta.persistence.dao.DAO;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 11/06/2020
 */

@Local
public interface AuditoriaService extends DAO<Auditoria, Long> {

   public static final String JNDI_NAME = "java:app/carpeta-ejb/AuditoriaEJB!es.caib.carpeta.ejb.AuditoriaService";

   /**
    * Crea una auditoria asociado a la entidad indicada
    *
    * @param auditoria auditoria.
    * @param entidadId entidad a la que se asocia.
    * @return La auditoria creada.
    */
   Auditoria crearAuditoria(Auditoria auditoria, Long entidadId) throws I18NException;


   /**
    * Obtiene una lista de todas los auditorias de una entidad
    *
    * @param entidadId identificador de la entidad
    * @return lista de auditorias de la entidad
    */
   List<Auditoria> findAllByEntidad(Long entidadId);

   /**
    * Obtiene el numero de auditorias de una entidad
    *
    * @param entidadId identificador de la entidad.
    * @return número de auditorias de la entidad.
    */
   Long countAllByEntidad(Long entidadId);


}
