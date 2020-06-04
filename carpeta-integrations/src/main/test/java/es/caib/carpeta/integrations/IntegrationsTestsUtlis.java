package es.caib.carpeta.integrations;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class IntegrationsTestsUtlis {

    private static Properties testProperties = new Properties();

    static {

        // Propietats del Servidor
        try {
            System.out.println(new File(".").getAbsolutePath());
            testProperties.load(new FileInputStream("test.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getSistra1Url() {
        return testProperties.getProperty("sistra1.urlBase");
    }

    public static String getSistra1User() {
        return testProperties.getProperty("sistra1.user");
    }

    public static String getSistra1Pass() {
        return testProperties.getProperty("sistra1.pass");
    }

    public static String getSistra1Documento() {
        return testProperties.getProperty("sistra1.documento");
    }

    public static String getSistra2Url() {
        return testProperties.getProperty("sistra2.urlBase");
    }

    public static String getSistra2User() {
        return testProperties.getProperty("sistra2.user");
    }

    public static String getSistra2Pass() {
        return testProperties.getProperty("sistra2.pass");
    }

    public static String getSistra2Documento() {
        return testProperties.getProperty("sistra2.documento");
    }

    public static String getRegWeb3Host() {
        return testProperties.getProperty("regweb3.host");
    }

    public static String getRegWeb3Api() {
        return testProperties.getProperty("regweb3.api");
    }

    public static String getRegWeb3Entidad() {
        return testProperties.getProperty("regweb3.entidad");
    }

    public static String getRegWeb3User() {
        return testProperties.getProperty("regweb3.user");
    }

    public static String getRegWeb3Pass() {
        return testProperties.getProperty("regweb3.pass");
    }
}
