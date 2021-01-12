package es.caib.carpeta.logic.utils;

import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.PropietatGlobalLocal;
import es.caib.carpeta.logic.AuditoriaLogicaLocal;
import es.caib.carpeta.logic.AuthenticationLogicaLocal;
import es.caib.carpeta.logic.AvisLogicaLocal;
import es.caib.carpeta.model.fields.PropietatGlobalFields;


/**
 * 
 * @author anadal
 * 
 */
public final class EjbManager {

	protected static final Logger log = Logger.getLogger(EjbManager.class);


	protected static PropietatGlobalLocal propietatLogicaEjb;
	
	protected static AvisLogicaLocal avisLogicaEjb;

	protected static AuditoriaLogicaLocal auditoriaLogicaEjb;

	protected static AuthenticationLogicaLocal authenticationLogicaEjb;


	private static void throwNewI18NException(Throwable e, String name) throws I18NException {
		throw new I18NException(e, "error.unknown",
				new I18NArgumentString("No puc instanciar " + name + ": " + e.getMessage()));
	}

	

	public static PropietatGlobalLocal getPropietatLogicaEJB() throws I18NException {

		if (propietatLogicaEjb == null) {
			try {
				propietatLogicaEjb = (PropietatGlobalLocal) new InitialContext()
						.lookup(PropietatGlobalLocal.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, PropietatGlobalLocal.JNDI_NAME);
			}
		}
		return propietatLogicaEjb;
	}
	
	public static AvisLogicaLocal getAvisLogicaEJB() throws I18NException {

		if (avisLogicaEjb == null) {
			try {
				avisLogicaEjb = (AvisLogicaLocal) new InitialContext()
					.lookup(AvisLogicaLocal.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "AvisLogicaLocal");
			}
		}
		return avisLogicaEjb;
	}


	public static AuthenticationLogicaLocal getAuthenticationLogicaEJB() throws I18NException {

		if (authenticationLogicaEjb == null) {
			try {
				authenticationLogicaEjb = (AuthenticationLogicaLocal) new InitialContext()
						.lookup(AuthenticationLogicaLocal.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "AuthenticationLogicaLocal");
			}
		}
		return authenticationLogicaEjb;
	}
	
	
	public static String getDefaultEntityCode(PropietatGlobalLocal propietatGlobalEjb) throws I18NException {
	    final String partialProp = "defaultentitycode";
	    
	    return getPropertyValue(propietatGlobalEjb, partialProp);
	    
	}

	public static String getCanviarDeFront(PropietatGlobalLocal propietatGlobalEjb) throws I18NException {
		final String partialProp = "canviardefront";

		return getPropertyValue(propietatGlobalEjb, partialProp);

	}

	public static Long getEsborrarLogs(PropietatGlobalLocal propietatGlobalEjb) throws I18NException {
		final String partialProp = "esborrarlogs.dies";

		return getPropertyValueLong(propietatGlobalEjb, partialProp);

	}

    public static Long getPropertyValueLong(PropietatGlobalLocal propietatGlobalEjb, String partialProp)
            throws I18NException {
        String val = getPropertyValue(propietatGlobalEjb, partialProp);
	    if (val== null || val.trim().length() == 0) {
	        return null;
	    }
	    try {
            return Long.parseLong(val);
        } catch (Exception e) {
            log.error("Error cercant propietat: defaultEntity", e);
            return null;
        }
    }
	
	
	public  static String getPropertyValue(PropietatGlobalLocal propietatGlobalEjb, String partialProp) throws I18NException {
	    Where w = Where.AND(
	            PropietatGlobalFields.CODI.equal(Constants.CARPETA_PROPERTY_BASE + partialProp),
	            PropietatGlobalFields.ENTITATID.isNull()
	            );
	    return propietatGlobalEjb.executeQueryOne(PropietatGlobalFields.VALUE, w);
	}
	
	
//    public static String getPropertyValue(PropietatGlobalLocal propietatGlobalEjb, String partialProp, long entitatID) {
//        
//    }
	
	

}
