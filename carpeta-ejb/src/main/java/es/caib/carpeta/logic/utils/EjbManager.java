package es.caib.carpeta.logic.utils;

import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.IdiomaService;
import es.caib.carpeta.ejb.PropietatGlobalService;
import es.caib.carpeta.logic.AuditoriaLogicaService;
import es.caib.carpeta.logic.AuthenticationLogicaService;
import es.caib.carpeta.logic.AvisLogicaService;
import es.caib.carpeta.logic.LogCarpetaLogicaService;
import es.caib.carpeta.model.fields.PropietatGlobalFields;


/**
 * 
 * @author anadal
 * 
 */
public final class EjbManager {

	protected static final Logger log = Logger.getLogger(EjbManager.class);


	protected static PropietatGlobalService propietatLogicaEjb;
	
	protected static AvisLogicaService avisLogicaEjb;
	
	protected static IdiomaService idiomaEjb;

	protected static AuditoriaLogicaService auditoriaLogicaEjb;

	protected static AuthenticationLogicaService authenticationLogicaEjb;
	
	protected static LogCarpetaLogicaService logCarpetaLogicaEjb;


	private static void throwNewI18NException(Throwable e, String name) throws I18NException {
		throw new I18NException(e, "error.unknown",
				new I18NArgumentString("No puc instanciar " + name + ": " + e.getMessage()));
	}

	
	public static LogCarpetaLogicaService getLogCarpetaLogicaEJB() throws I18NException {

        if (logCarpetaLogicaEjb == null) {
            try {
                logCarpetaLogicaEjb = (LogCarpetaLogicaService) new InitialContext()
                        .lookup(LogCarpetaLogicaService.JNDI_NAME);
            } catch (Throwable e) {
                throwNewI18NException(e, LogCarpetaLogicaService.JNDI_NAME);
            }
        }
        return logCarpetaLogicaEjb;
    }
	
	
	public static IdiomaService getIdiomaEJB() throws I18NException {

        if (idiomaEjb == null) {
            try {
                idiomaEjb = (IdiomaService) new InitialContext()
                        .lookup(IdiomaService.JNDI_NAME);
            } catch (Throwable e) {
                throwNewI18NException(e, IdiomaService.JNDI_NAME);
            }
        }
        return idiomaEjb;
    }
	

	public static PropietatGlobalService getPropietatLogicaEJB() throws I18NException {

		if (propietatLogicaEjb == null) {
			try {
				propietatLogicaEjb = (PropietatGlobalService) new InitialContext()
						.lookup(PropietatGlobalService.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, PropietatGlobalService.JNDI_NAME);
			}
		}
		return propietatLogicaEjb;
	}
	
	public static AvisLogicaService getAvisLogicaEJB() throws I18NException {

		if (avisLogicaEjb == null) {
			try {
				avisLogicaEjb = (AvisLogicaService) new InitialContext()
					.lookup(AvisLogicaService.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "AvisLogicaService");
			}
		}
		return avisLogicaEjb;
	}


	public static AuthenticationLogicaService getAuthenticationLogicaEJB() throws I18NException {

		if (authenticationLogicaEjb == null) {
			try {
				authenticationLogicaEjb = (AuthenticationLogicaService) new InitialContext()
						.lookup(AuthenticationLogicaService.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "AuthenticationLogicaService");
			}
		}
		return authenticationLogicaEjb;
	}
	
	public static String getAccesosMaxDies(PropietatGlobalService propietatGlobalEjb) throws I18NException {
		final String partialProp = "accesosmaxdies";
		String valor = getPropertyValue(propietatGlobalEjb, partialProp); 
		return ( valor != null && valor != "") ? valor : "365";
	}
	
	public static String getDefaultEntityCode(PropietatGlobalService propietatGlobalEjb) throws I18NException {
	    final String partialProp = "defaultentitycode";
	    
	    return getPropertyValue(propietatGlobalEjb, partialProp);
	    
	}

	public static String getCanviarDeFront(PropietatGlobalService propietatGlobalEjb) throws I18NException {
		final String partialProp = "canviardefront";

		return getPropertyValue(propietatGlobalEjb, partialProp);

	}
	
	public static String getEsborrarLogsHora(PropietatGlobalService propietatGlobalEjb) throws I18NException {
		final String partialProp = "esborrarlogs.hora";
		
		return getPropertyValue(propietatGlobalEjb, partialProp);
		
	}
	
	public static Long getEsborrarLogsDies(PropietatGlobalService propietatGlobalEjb) throws I18NException {
		final String partialProp = "esborrarlogs.dies";

		return getPropertyValueLong(propietatGlobalEjb, partialProp);

	}

    public static Long getPropertyValueLong(PropietatGlobalService propietatGlobalEjb, String partialProp)
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
	
	
	public  static String getPropertyValue(PropietatGlobalService propietatGlobalEjb, String partialProp) throws I18NException {
	    Where w = Where.AND(
	            PropietatGlobalFields.CODI.equal(Constants.CARPETA_PROPERTY_BASE + partialProp),
	            PropietatGlobalFields.ENTITATID.isNull()
	            );
	    return propietatGlobalEjb.executeQueryOne(PropietatGlobalFields.VALUE, w);
	}
	
	
//    public static String getPropertyValue(PropietatGlobalService propietatGlobalEjb, String partialProp, long entitatID) {
//        
//    }
	
	

}
