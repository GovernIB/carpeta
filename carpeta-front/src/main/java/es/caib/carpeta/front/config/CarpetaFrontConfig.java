package es.caib.carpeta.front.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"es.caib.carpeta.front"})
public class CarpetaFrontConfig extends WebMvcConfigurerAdapter {

// REACT
    @Override
    public void addResourceHandlers(
            ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/dist/**")
                .addResourceLocations("/dist/");
        registry.addResourceHandler("/src/**")
                .addResourceLocations("/src/");
        registry.addResourceHandler("/*.js")
                .addResourceLocations("/src/assets/js/");
        registry.addResourceHandler("/*.json")
                .addResourceLocations("/");
        registry.addResourceHandler("/*.ico")
                .addResourceLocations("/");
        registry.addResourceHandler("/index.html")
                .addResourceLocations("/index.html");
    }



    // JSP
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }


    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("missatges");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean(name = "localeResolver")
    public SessionLocaleResolver getSessionLocaleResolver(){
        // Create a SessionLocaleResolver object.
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        // Set default locale in session.
        localeResolver.setDefaultLocale(new Locale("ca"));
        return localeResolver;
    }
//
//    @Bean(name="localeInterceptor")
//    public LocaleChangeInterceptor getLocaleInterceptor(){
//        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
//        interceptor.setParamName("lng");
//        System.out.println("interceptor: " + interceptor.getParamName());
//        return interceptor;
//    }

}