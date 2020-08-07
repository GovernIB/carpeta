package es.caib.carpeta.commons.utils;

import es.caib.carpeta.utils.Version;

/**
* Codi autogenerat a partir del fitxer StaticVersion.java.template.
*/
public interface StaticVersion {
	
	public static Version version = new Version();

    public static final String VERSION= version.getVersion();

    public static final String PROJECT_NAME= version.getProjectName();

}