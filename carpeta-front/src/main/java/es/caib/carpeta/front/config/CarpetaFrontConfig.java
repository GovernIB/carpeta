package es.caib.carpeta.front.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"es.caib.carpeta.front"})
public class CarpetaFrontConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addResourceHandlers(
            ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static/");
        registry.addResourceHandler("/*.js")
                .addResourceLocations("/static/js/");
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
    public ViewResolver resourceBundleViewResolver() {
        ResourceBundleViewResolver bean = new ResourceBundleViewResolver();
        bean.setBasename("views/pages");
        return bean;
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
//
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
//        configurer.enable();
//    }
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/inicio");
//    }
//
//    @Bean
//    public ResourceBundleMessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("mensajes");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }
//
//    @Bean(name = "localeResolver")
//    public SessionLocaleResolver getSessionLocaleResolver(){
//        // Create a SessionLocaleResolver object.
//        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//        // Set default locale in session.
//        localeResolver.setDefaultLocale(new Locale("ca"));
//        return localeResolver;
//    }
//
//    @Bean(name="localeInterceptor")
//    public LocaleChangeInterceptor getLocaleInterceptor(){
//        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
//        interceptor.setParamName("lang");
//        return interceptor;
//    }
//
//    @Override
//    public void addFormatters (FormatterRegistry registry) {
//        DateFormatter dateFormatter = new DateFormatter();
//        dateFormatter.setPattern("dd/MM/yyyy");
//
//        registry.addFormatter(dateFormatter);
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getLocaleInterceptor());
//    }
//
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//
//        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//
//    }

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer properties(){
//        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
//        pspc.setLocation( new FileSystemResource(System.getProperty("es.caib.carpeta.properties.path")));
//
//        pspc.setIgnoreUnresolvablePlaceholders( true );
//        return pspc;
//    }

}