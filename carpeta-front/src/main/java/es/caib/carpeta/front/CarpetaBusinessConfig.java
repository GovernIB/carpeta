package es.caib.carpeta.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan
//@PropertySource("file:${es.caib.carpeta.properties.path}")
//@EnableJpaRepositories("es.caib.carpeta.front")
public class CarpetaBusinessConfig {

//    @Value("${es.caib.carpeta.db.driver}")
//    private String db_driver;
//
//    @Value("${es.caib.carpeta.db.url}")
//    private String db_url;
//
//    @Value("${es.caib.carpeta.db.username}")
//    private String db_username;
//
//    @Value("${es.caib.carpeta.db.password}")
//    private String db_password;
//
//    @Value("${es.caib.carpeta.hibernate.dialect}")
//    private String hibernate_dialect;
//
//    @Value("${es.caib.carpeta.hibernate.generateDdl}")
//    private Boolean hibernate_generateDdl;
//
//    @Value("${es.caib.carpeta.hibernate.hbm2ddl.auto}")
//    private String hibernate_hbm2ddl_auto;
//
//    @Value("${es.caib.carpeta.hibernate.show_sql}")
//    private Boolean hibernate_show_sql;
//
//    @Value("${es.caib.carpeta.hibernate.format_sql}")
//    private Boolean hibernate_format_sql;

//    private String db_driver = "postgresql";
//
//    private String db_url = "jdbc:postgresql://localhost:5432/carpeta";
//
//    private String db_username = "carpeta";
//
//    private String db_password = "carpeta";
//
//    private String hibernate_dialect;
//
//    private Boolean hibernate_generateDdl = true;
//
//    private String hibernate_hbm2ddl_auto;
//
//    private Boolean hibernate_show_sql = true;
//
//    private Boolean hibernate_format_sql = true;
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(db_driver);
//        dataSource.setUrl(db_url);
//        dataSource.setUsername(db_username);
//        dataSource.setPassword(db_password);
//        return dataSource;
//    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setPersistenceUnitName("carpeta");
//        emf.setDataSource(dataSource());
//        emf.setPackagesToScan("es.caib.carpeta.core.model");
//
//        HibernateJpaVendorAdapter hibernateJpa = new HibernateJpaVendorAdapter();
//        hibernateJpa.setDatabase(Database.POSTGRESQL);
//        hibernateJpa.setDatabasePlatform(hibernate_dialect);
//        hibernateJpa.setGenerateDdl(hibernate_generateDdl);
//        hibernateJpa.setShowSql(hibernate_show_sql);
//        emf.setJpaVendorAdapter(hibernateJpa);
//
//        Properties jpaProperties = new Properties();
//        jpaProperties.put("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
//        jpaProperties.put("hibernate.show_sql", hibernate_show_sql);
//        jpaProperties.put("hibernate.format_sql", hibernate_format_sql);
//        emf.setJpaProperties(jpaProperties);
//
//        return emf;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager txnMgr = new JpaTransactionManager();
//        txnMgr.setEntityManagerFactory(entityManagerFactory().getObject());
//        return txnMgr;
//    }

  /*  @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        pspc.setLocation( new FileSystemResource(System.getProperty("es.caib.carpeta.properties.path")));

        pspc.setIgnoreUnresolvablePlaceholders( true );
        return pspc;
    }*/

}
