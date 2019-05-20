package es.caib.carpeta.back.config;

import es.caib.carpeta.core.service.ProductoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.ejb.access.LocalStatelessSessionProxyFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(value = {"es.caib.carpeta.back"})
@EnableWebMvc
public class CarpetaBackConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("mensajes");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/css/**")
                .addResourceLocations("/css/");

        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("/fonts/");

        registry.addResourceHandler("/imgs/**")
                .addResourceLocations("/imgs/");

        registry.addResourceHandler("/js/**")
                .addResourceLocations("/js/");
    }

   /* @Bean
    public LocalStatelessSessionProxyFactoryBean productoService(){
        LocalStatelessSessionProxyFactoryBean lslbs = new LocalStatelessSessionProxyFactoryBean();

        lslbs.setBusinessInterface(ProductoService.class);
        lslbs.setJndiName("java:module/ProductoServiceEjb");

        return lslbs;
    }*/

}