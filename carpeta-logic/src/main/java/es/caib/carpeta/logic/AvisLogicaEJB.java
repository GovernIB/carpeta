package es.caib.carpeta.logic;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Stateless;

import es.caib.carpeta.ejb.AvisEJB;
import es.caib.carpeta.model.entity.Avis;
import es.caib.carpeta.model.fields.AvisQueryPath;
import es.caib.carpeta.model.fields.AvisFields;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;

/**
 * 
 * @author jagarcia
 * 
 */
@Stateless
public class AvisLogicaEJB extends AvisEJB  implements AvisLogicaService {
	
	@Override
	public List<Avis> findAllActive () throws I18NException {
		
	    Where w = getDatesWhere();
	    
	    OrderBy o = new OrderBy(AvisFields.GRAVETAT, OrderType.DESC);
	    
	    return select(w, o);
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
	    
	    Where w1 = getDatesWhere();
	    Where w2 = AvisFields.ENTITATID.equal(entitatID);
        
	    Where w = Where.AND(w1,w2);
	    
	    OrderBy o = new OrderBy(AvisFields.GRAVETAT, OrderType.DESC);
	    
	    return select(w, o);

	 }

	/** Cerca els avisos actius d'un plugin en concret i els ordena de més greu a menys **/
	@Override
	public List<Avis> findActiveByPluginID (long pluginID) throws I18NException {
		
		Where w1 = getDatesWhere();
		Where w2 = AvisFields.PLUGINFRONTID.equal(pluginID);
		Where w = Where.AND(w1,w2);
		
		OrderBy o = new OrderBy(AvisFields.GRAVETAT, OrderType.DESC);
		
	    return select(w, o);
		
	}

	/** Cerca els avisos actius d'un front public en concret i els ordena de més greu a menys **/
	@Override
	public List<Avis> findActiveAvisos (String codiEntitat, int avisType) throws I18NException {
			
		Where w1 = getDatesWhere();
		AvisQueryPath aqp = new AvisQueryPath();
		
        Where w2 = aqp.ENTITAT().CODI().equal(codiEntitat);
		Where w3 = AvisFields.TIPUS.equal(avisType);
		Where w = Where.AND(w1,w2,w3);
		
		OrderBy o = new OrderBy(AvisFields.GRAVETAT, OrderType.DESC);
		
	    return select(w, o);
		
	}

}
