package es.caib.carpeta.back.config;

import es.caib.carpeta.core.CarpetaBusinessConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class CarpetaBackAppInitilizer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { CarpetaBusinessConfig.class/*, CarpetaSecurityConfig.class*/ };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { CarpetaBackConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}