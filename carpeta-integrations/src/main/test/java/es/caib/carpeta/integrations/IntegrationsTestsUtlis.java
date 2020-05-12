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


    public static String getSistraUrl() {
        return testProperties.getProperty("sistra.urlBase");
    }

    public static String getSistraUser() {
        return testProperties.getProperty("sistra.user");
    }

    public static String getSistraPass() {
        return testProperties.getProperty("sistra.pass");
    }

    public static String getSistraDocumento() {
        return testProperties.getProperty("sistra.documento");
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
