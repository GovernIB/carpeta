package es.caib.carpeta.api.utils;

import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;

/**
 * Clase d'utilitat per traduir missatges I18N dins el mòdul API REST.
 *
 * @author anadal
 * @author areus
 */
public class I18NTranslatorRest extends I18NCommonUtils {

	static {
		BUNDLES = new String[] { "ValidationMessages", "persistence.LabelsPersistence", "ejb.LabelsEJB", "genapp" };
	}

}

