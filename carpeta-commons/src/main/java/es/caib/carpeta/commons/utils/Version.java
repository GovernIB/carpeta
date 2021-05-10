package es.caib.carpeta.commons.utils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ResourceBundle;

/**
 * Bean amb dades de la versió. Serà el mateix per tothom per tant el definim dins l'scope
 * d'aplicació. Les agafa del fitxer Vesion.properties del mateix package.
 *
 * @author areus
 * @author anadal
 */
@Named
@ApplicationScoped
public class Version {

    private String version;
    private String buildTime;
    private String scmRevision;
    private String jdkVersion;
    private String projectName;

    /**
     * Inicialitza el bean amb els valors de Version.properties
     */
    @PostConstruct
    public void init() {
        /* Agafa fitxer Version.properties amb el mateix package */
        ResourceBundle bundle = ResourceBundle.getBundle("carpeta.version.Version");
        version = bundle.getString("project.version");
        buildTime = bundle.getString("project.buildtime");
        scmRevision = bundle.getString("scm.revision");
        jdkVersion = bundle.getString("jdk.version");
        projectName = bundle.getString("project.name");
        if (projectName != null && projectName.length() != 0) {
        	projectName = ("" + projectName.charAt(0)).toUpperCase() + projectName.substring(1);
        }
        
    }

    /**
     * Obté la versió del projecte
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Obté el moment de compilació del projecte
     */
    public String getBuildTime() {
        return this.buildTime;
    }

    /**
     * Obté la revisió del sistema de control de versions
     */
    public String getScmRevision() {
        return this.scmRevision;
    }

    /**
     * Obté el JDK amb el que es va compilar el projecte
     */
    public String getJdkVersion() {
        return this.jdkVersion;
    }

    /**
     * Obté el nom del projecte
     */
    public String getProjectName() {
        return this.projectName;
    }
}