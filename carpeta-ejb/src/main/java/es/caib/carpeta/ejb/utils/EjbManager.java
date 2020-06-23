package es.caib.carpeta.ejb.utils;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.ejb.EntidadService;
import es.caib.carpeta.ejb.UsuarioEntidadService;
import es.caib.carpeta.ejb.UsuarioService;


/**
 * 
 * @author anadal
 * 
 */
public final class EjbManager {

	protected static final Logger log = Logger.getLogger(EjbManager.class);

	protected static UsuarioEntidadService usuariEntitatLogicaEjb;

	protected static UsuarioService usuariPersonaLogicaEjb;
	
	protected static EntidadService entitatLogicaEjb;
/* XYZ ZZZ ZZZ
	protected static IdiomaLocal idiomaEjb;

	protected static PropietatGlobalLocal propietatLogicaEjb;
	*/

	private static void throwNewI18NException(Throwable e, String name) throws I18NException {
		throw new I18NException(e, "error.unknown",
				new I18NArgumentString("No puc instanciar " + name + ": " + e.getMessage()));
	}

	public static UsuarioEntidadService getUsuariEntitatLogicaEJB() throws I18NException {

		if (usuariEntitatLogicaEjb == null) {
			try {
				usuariEntitatLogicaEjb = (UsuarioEntidadService) new InitialContext()
						.lookup(UsuarioEntidadService.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "UsuariEntitatLogicaLocal");
			}
		}
		return usuariEntitatLogicaEjb;
	}

	public static UsuarioService getUsuariPersonaLogicaEJB() throws I18NException {

		if (usuariPersonaLogicaEjb == null) {
			try {
				usuariPersonaLogicaEjb = (UsuarioService) new InitialContext()
						.lookup(UsuarioService.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "UsuariPersonaLogicaLocal");
			}
		}
		return usuariPersonaLogicaEjb;
	}
	
	
	public static EntidadService getEntitatLogicaEJB() throws I18NException {

		if (entitatLogicaEjb == null) {
			try {
				entitatLogicaEjb = (EntidadService) new InitialContext()
						.lookup(EntidadService.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "EntitatLogicaLocal");
			}
		}
		return entitatLogicaEjb;
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

	public static PropietatGlobalLogicaLocal getPropietatLogicaEJB() throws I18NException {

		if (propietatLogicaEjb == null) {
			try {
				propietatLogicaEjb = (PropietatGlobalLogicaLocal) new InitialContext()
						.lookup(PropietatGlobalLogicaLocal.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, PropietatGlobalLogicaLocal.JNDI_NAME);
			}
		}
		return propietatLogicaEjb;
	}
*/
}
