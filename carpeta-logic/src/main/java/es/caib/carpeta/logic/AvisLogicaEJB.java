package es.caib.carpeta.logic;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import es.caib.carpeta.ejb.AvisEJB;
import es.caib.carpeta.model.entity.Avis;
import es.caib.carpeta.model.fields.AvisFields;
import es.caib.carpeta.persistence.AvisJPA;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.hibernate.Hibernate;

/**
 * 
 * @author jagarcia
 * 
 */
@Stateless
public class AvisLogicaEJB extends AvisEJB  implements AvisLogicaService {
	
	@Override
	public List<Avis> findAllActive () throws I18NException {
		 /*
		 TypedQuery<AvisJPA> query = getEntityManager().createQuery(
					"select a from AvisJPA a join fetch a.entitat "
					+ "where (CURRENT_DATE between a.dataInici and a.dataFi) "
					+ "   or (a.dataFi IS NULL and a.dataInici IS NULL)"
					+ "   or (a.dataFi IS NULL and CURRENT_DATE >= a.dataInici)"
					+ "   or (a.dataInici IS NULL and CURRENT_DATE < a.dataFi)"
					+ " order by a.gravetat desc", AvisJPA.class);
			return query.getResultList(); 
		*/		

	    Where w = getDatesWhere();
		List<Avis> llistat = select(w);
		for (Avis avis : llistat) {
			Hibernate.initialize(((AvisJPA)avis).getEntitat());
		}
	    return llistat;
	 }

    protected Where getDatesWhere() {
        Timestamp current = new Timestamp(System.currentTimeMillis());
        		
             Where w1 = Where.AND(DATAINICI.lessThanOrEqual(current), DATAFI.greaterThanOrEqual(current));
             Where w2 = Where.AND(DATAINICI.isNull(), DATAFI.isNull());
             Where w3 = Where.AND(DATAINICI.isNull(), DATAFI.greaterThanOrEqual(current));
             Where w4 = Where.AND(DATAINICI.lessThanOrEqual(current), DATAFI.isNull());
             
             Where w = Where.OR(w1,w2,w3,w4);
        return w;
    }
	
	@Override
    public List<Avis> findActiveByEntidadID (long entitatID) throws I18NException {
		/*
		TypedQuery<AvisJPA> query = getEntityManager().createQuery(
					"select a from AvisJPA a "

	 public List<AvisJPA> findActiveByEntidadID (long entidadID) throws I18NException {
		 
		 TypedQuery<AvisJPA> query = getEntityManager().createQuery(
					"select a from AvisJPA a join fetch a.entitat "
					+ "where a.entitatID = :entidad " 
					+ "and ((CURRENT_DATE between a.dataInici and a.dataFi)"
					+ " or (a.dataFi IS NULL and a.dataInici IS NULL)"
					+ " or (a.dataFi IS NULL and CURRENT_DATE >= a.dataInici)"

					+ " or (a.dataInici IS NULL and CURRENT_DATE < a.dataFi))", AvisJPA.class);
	 	query.setParameter("entidad", entidadID);
	 	
		return query.getResultList();
		*/
	    
	    Where w1 = getDatesWhere();
	    Where w2 = AvisFields.ENTITATID.equal(entitatID);
        
	    Where w = Where.AND(w1,w2);
	    
	    List<Avis> llistat = select(w);
		for (Avis avis : llistat) {
			Hibernate.initialize(((AvisJPA)avis).getEntitat());
		}
	    return llistat;

	 }

	/** Cerca els avisos actius d'un plugin en concret i els ordena de més greu a menys **/
	@Override
	public List<AvisJPA> findActiveByPluginID (long pluginID) throws I18NException {

		TypedQuery<AvisJPA> query = getEntityManager().createQuery(
				"select a from AvisJPA a "
						+ "where a.pluginFrontID = :plugin "
						+ "and ((CURRENT_DATE between a.dataInici and a.dataFi) "
						+ " or (a.dataFi IS NULL and a.dataInici IS NULL)"
						+ " or (a.dataFi IS NULL and CURRENT_DATE >= a.dataInici)"
						+ " or (a.dataInici IS NULL and CURRENT_DATE < a.dataFi))"
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
						+ "and ((CURRENT_DATE between a.dataInici and a.dataFi) "
						+ " or (a.dataFi IS NULL and a.dataInici IS NULL)"
						+ " or (a.dataFi IS NULL and CURRENT_DATE >= a.dataInici)"
						+ " or (a.dataInici IS NULL and CURRENT_DATE < a.dataFi))"
						+ "and a.tipus = :avisType "
						+ "order by a.gravetat desc", AvisJPA.class);
		query.setParameter("codiEntitat", codiEntitat);
		query.setParameter("avisType", avisType);
		return query.getResultList();
	}

}
