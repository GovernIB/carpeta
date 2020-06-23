package es.caib.carpeta.ejb.utils;

import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;

/**
 * Clase d'utilitat per traduir missatges I18N dins el mòdul EJB
 *
 * @author anadal
 */
public class I18NTranslatorEjb extends I18NCommonUtils {

	static {
		BUNDLES = new String[] { "ValidationMessages", "persistence.LabelsPersistence", "ejb.LabelsEJB", "genapp" };
	}

}
