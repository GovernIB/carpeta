package es.caib.carpeta.front.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(value = {"es.caib.carpeta.front"})
public class CarpetaFrontConfig extends WebMvcConfigurerAdapter {

    /**
     * Configure TilesConfigurer.
     */
    @Bean
    public TilesConfigurer tilesConfigurer(){
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/views/**/tiles.xml"});
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }

    /*@Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }*/

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/inici");
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

        registry.addResourceHandler("/static/**").addResourceLocations("/static/");


       /*registry.addResourceHandler("/css/**")
                .addResourceLocations("/css/");

        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("/fonts/");

        registry.addResourceHandler("/imgs/**")
                .addResourceLocations("/imgs/");

        registry.addResourceHandler("/js/**")
                .addResourceLocations("/js/");*/
    }

    /*@Bean
    public LocalStatelessSessionProxyFactoryBean securityService(){
        LocalStatelessSessionProxyFactoryBean securityService = new LocalStatelessSessionProxyFactoryBean();

        securityService.setBusinessInterface(SecurityService.class);
        securityService.setJndiName("java:module/SecurityServiceEjb");

        return securityService;
    }

    @Bean
    public LocalStatelessSessionProxyFactoryBean productoService(){
        LocalStatelessSessionProxyFactoryBean lslbs = new LocalStatelessSessionProxyFactoryBean();

        lslbs.setBusinessInterface(ProductoService.class);
        lslbs.setJndiName("java:module/ProductoServiceEjb");

        return lslbs;
    }*/



    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        pspc.setLocation( new FileSystemResource(System.getProperty("es.caib.carpeta.properties.path")));

        pspc.setIgnoreUnresolvablePlaceholders( true );
        return pspc;
    }

}