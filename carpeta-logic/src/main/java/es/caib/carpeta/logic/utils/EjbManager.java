package es.caib.carpeta.logic.utils;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.EntitatLocal;
import es.caib.carpeta.ejb.PropietatGlobalLocal;
import es.caib.carpeta.logic.LogCarpetaLogicaLocal;
import es.caib.carpeta.logic.UsuariEntitatLogicaLocal;
import es.caib.carpeta.logic.UsuariLogicaLocal;
import es.caib.carpeta.model.fields.PropietatGlobalFields;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import javax.naming.InitialContext;


/**
 * 
 * @author anadal
 * 
 */
public final class EjbManager {

	protected static final Logger log = Logger.getLogger(EjbManager.class);

	protected static UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

	protected static UsuariLogicaLocal usuariPersonaLogicaEjb;
	
	protected static EntitatLocal entitatLogicaEjb;

	protected static LogCarpetaLogicaLocal logCarpetaLogicaEjb;
/* XYZ ZZZ ZZZ
	protected static IdiomaLocal idiomaEjb;
    */
	protected static PropietatGlobalLocal propietatLogicaEjb;


	private static void throwNewI18NException(Throwable e, String name) throws I18NException {
		throw new I18NException(e, "error.unknown",
				new I18NArgumentString("No puc instanciar " + name + ": " + e.getMessage()));
	}

	public static UsuariEntitatLogicaLocal getUsuariEntitatLogicaEJB() throws I18NException {

		if (usuariEntitatLogicaEjb == null) {
			try {
				usuariEntitatLogicaEjb = (UsuariEntitatLogicaLocal) new InitialContext()
						.lookup(UsuariEntitatLogicaLocal.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "UsuariEntitatLogicaLocal");
			}
		}
		return usuariEntitatLogicaEjb;
	}

	public static UsuariLogicaLocal getUsuariPersonaLogicaEJB() throws I18NException {

		if (usuariPersonaLogicaEjb == null) {
			try {
				usuariPersonaLogicaEjb = (UsuariLogicaLocal) new InitialContext()
						.lookup(UsuariLogicaLocal.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "UsuariPersonaLogicaLocal");
			}
		}
		return usuariPersonaLogicaEjb;
	}
	
	
	public static EntitatLocal getEntitatLogicaEJB() throws I18NException {

		if (entitatLogicaEjb == null) {
			try {
				entitatLogicaEjb = (EntitatLocal) new InitialContext()
						.lookup(EntitatLocal.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "EntitatLogicaLocal");
			}
		}
		return entitatLogicaEjb;
	}


	public static LogCarpetaLogicaLocal getLogCarpetaLogicaEJB() throws I18NException {

		if (logCarpetaLogicaEjb == null) {
			try {
				logCarpetaLogicaEjb = (LogCarpetaLogicaLocal) new InitialContext()
					.lookup(LogCarpetaLogicaLocal.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "LogCarpetaLogicaLocal");
			}
		}
		return logCarpetaLogicaEjb;
	}
	
/* XYZ ZZZ ZZZ
	public static IdiomaLocal getIdiomaEJB() throws I18NException {

		if (idiomaEjb == null) {
			try {
				idiomaEjb = (IdiomaLocal) new InitialContext().lookup("portafib/IdiomaEJB/local");
			} catch (Throwable e) {
				throwNewI18NException(e, "IdiomaEJB");
			}
		}
		return idiomaEjb;
	}
	*/

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
	
	
	public static String getDefaultEntityCode(PropietatGlobalLocal propietatGlobalEjb) throws I18NException {
	    final String partialProp = "defaultentitycode";
	    
	    return getPropertyValue(propietatGlobalEjb, partialProp);
	    
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
