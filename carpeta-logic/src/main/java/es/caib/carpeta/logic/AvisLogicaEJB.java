package es.caib.carpeta.logic;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import es.caib.carpeta.ejb.AvisEJB;
import es.caib.carpeta.jpa.AvisJPA;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author jagarcia
 * 
 */
@Stateless
public class AvisLogicaEJB extends AvisEJB  implements AvisLogicaLocal {
	
	public List<AvisJPA> findAllActive () throws I18NException {
		 
		 TypedQuery<AvisJPA> query = getEntityManager().createQuery(
					"select a from AvisJPA a "
					+ "where CURRENT_DATE between a.dataInici and a.dataFi", AvisJPA.class);
			return query.getResultList(); 
	 }
	
	
	 public List<AvisJPA> findActiveByEntidadID (long entidadID) throws I18NException {
		 
		 TypedQuery<AvisJPA> query = getEntityManager().createQuery(
					"select a from AvisJPA a "
					+ "where a.entitatID = :entidad " 
					+ "and CURRENT_DATE between a.dataInici and a.dataFi", AvisJPA.class);
		 	query.setParameter("entidad", entidadID);
			return query.getResultList(); 
	 }

}
