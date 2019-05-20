package es.caib.carpeta.back;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class CarpetaTestUtils {

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


    public static String getLoginIbMetodosAuth() {
        return testProperties.getProperty("loginib.metodos_auth");
    }

    public static String getLoginIbEntidad() {
        return testProperties.getProperty("loginib.entidad");
    }

    public static String getLoginIbAplicacion() {
        return testProperties.getProperty("loginib.aplicacion");
    }

    public static String getLoginIbUrlCallBackLogin() {
        return testProperties.getProperty("loginib.url_callback_login");
    }

    public static String getLoginIbUrlCallBackLogout() {
        return testProperties.getProperty("loginib.url_callback_logout");
    }

    public static String getLoginIbUrlCallBackError() {
        return testProperties.getProperty("loginib.url_callback_error");
    }

    public static String getLoginIbIdioma() {
        return testProperties.getProperty("loginib.idioma");
    }

    public static String getLoginIbNivelQaa() {
        return testProperties.getProperty("loginib.nivel_qaa");
    }

    public static String getLoginIbUrl() {
        return testProperties.getProperty("loginib.url");
    }

    public static String getLoginIbUser() {
        return testProperties.getProperty("loginib.user");
    }

    public static String getLoginIbPass() {
        return testProperties.getProperty("loginib.pass");
    }
}
