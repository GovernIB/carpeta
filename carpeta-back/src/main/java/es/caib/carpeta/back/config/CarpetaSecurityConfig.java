package es.caib.carpeta.back.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesUserDetailsService;
import org.springframework.security.web.authentication.preauth.j2ee.J2eeBasedPreAuthenticatedWebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.j2ee.J2eePreAuthenticatedProcessingFilter;


import java.util.*;

@Configuration
@EnableWebSecurity
public class CarpetaSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(preauthAuthProvider());

        /*auth.inMemoryAuthentication().
                withUser("earrivi").
                password("earrivi").
                roles("CAR_ADMIN","CAR_SUPORT","CAR_USUARI");*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(j2eePreAuthenticatedProcessingFilter(),J2eePreAuthenticatedProcessingFilter.class)
                .authenticationProvider(preauthAuthProvider())
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public PreAuthenticatedAuthenticationProvider preauthAuthProvider() {
        PreAuthenticatedAuthenticationProvider preauthAuthProvider =
                new PreAuthenticatedAuthenticationProvider();
        preauthAuthProvider.setPreAuthenticatedUserDetailsService(
                new PreAuthenticatedGrantedAuthoritiesUserDetailsService()
        );
        return preauthAuthProvider;
    }

    @Bean
    J2eePreAuthenticatedProcessingFilter j2eePreAuthenticatedProcessingFilter() throws Exception {
        J2eePreAuthenticatedProcessingFilter filter = new J2eePreAuthenticatedProcessingFilter();

        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationDetailsSource(authenticationDetailsSource());

        return filter;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        final List<AuthenticationProvider> providers = new ArrayList<>(1);
        providers.add(preauthAuthProvider());

        return new ProviderManager(providers);
    }

    @Bean
    protected AuthenticationDetailsSource authenticationDetailsSource() throws Exception{
        J2eeBasedPreAuthenticatedWebAuthenticationDetailsSource detailsSource = new J2eeBasedPreAuthenticatedWebAuthenticationDetailsSource();

        detailsSource.setMappableRolesRetriever(mappableAttributesRetriever());
        detailsSource.setUserRoles2GrantedAuthoritiesMapper(attributes2GrantedAuthoritiesMapper());

        return detailsSource;
    }

    @Bean
    protected RolesBasedAttributes2GrantedAuthoritiesMapper attributes2GrantedAuthoritiesMapper() throws Exception{
       RolesBasedAttributes2GrantedAuthoritiesMapper attributes2GrantedAuthoritiesMapper =  new RolesBasedAttributes2GrantedAuthoritiesMapper();

        Map baseRoleMapping = new HashMap();
        baseRoleMapping.put("CAR_ADMIN","CAR_ADMIN");
        baseRoleMapping.put("CAR_SUPORT","CAR_SUPORT");
        baseRoleMapping.put("CAR_USUARI","CAR_USUARI");

        attributes2GrantedAuthoritiesMapper.setBaseRoleMapping(baseRoleMapping);

        return attributes2GrantedAuthoritiesMapper;

    }

    @Bean
    protected RolesBasedMappableAttributesRetriever mappableAttributesRetriever() throws Exception{
        RolesBasedMappableAttributesRetriever attributesRetriever = new RolesBasedMappableAttributesRetriever();

        Set<String> defaultMappableAttributes = new HashSet<>();

        defaultMappableAttributes.add("CAR_ADMIN");
        defaultMappableAttributes.add("CAR_SUPORT");
        defaultMappableAttributes.add("CAR_USUARI");
        attributesRetriever.setDefaultMappableAttributes(defaultMappableAttributes);

        return attributesRetriever;
    }


}
