package es.caib.carpeta.logic;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import es.caib.carpeta.ejb.AvisEJB;
import es.caib.carpeta.persistence.AvisJPA;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author jagarcia
 * 
 */
@Stateless
public class AvisLogicaEJB extends AvisEJB  implements AvisLogicaLocal {
	
	@Override
	public List<AvisJPA> findAllActive () throws I18NException {
		 
		 TypedQuery<AvisJPA> query = getEntityManager().createQuery(
					"select a from AvisJPA a "
					+ "where CURRENT_DATE between a.dataInici and a.dataFi", AvisJPA.class);
			return query.getResultList(); 
	 }
	
	@Override
	 public List<AvisJPA> findActiveByEntidadID (long entidadID) throws I18NException {
		 
		 TypedQuery<AvisJPA> query = getEntityManager().createQuery(
					"select a from AvisJPA a "
					+ "where a.entitatID = :entidad " 
					+ "and CURRENT_DATE between a.dataInici and a.dataFi", AvisJPA.class);
		 	query.setParameter("entidad", entidadID);
			return query.getResultList(); 
	 }

	/** Cerca els avisos actius d'un plugin en concret i els ordena de més greu a menys **/
	@Override
	public List<AvisJPA> findActiveByPluginID (long pluginID) throws I18NException {

		TypedQuery<AvisJPA> query = getEntityManager().createQuery(
				"select a from AvisJPA a "
						+ "where a.pluginFrontID = :plugin "
						+ "and CURRENT_DATE between a.dataInici and a.dataFi "
						+ "order by a.gravetat desc", AvisJPA.class);
		query.setParameter("plugin", pluginID);
		return query.getResultList();
	}

	/** Cerca els avisos actius d'un front public en concret i els ordena de més greu a menys **/
	@Override
	public List<AvisJPA> findActiveAvisos (String codiEntitat, int avisType) throws I18NException {

		TypedQuery<AvisJPA> query = getEntityManager().createQuery(
				"select a from AvisJPA a "
						+ "where a.entitat.codi = :codiEntitat "
						+ "and CURRENT_DATE between a.dataInici and a.dataFi "
						+ "and a.tipus = :avisType "
						+ "order by a.gravetat desc", AvisJPA.class);
		query.setParameter("codiEntitat", codiEntitat);
		query.setParameter("avisType", avisType);
		return query.getResultList();
	}

}
