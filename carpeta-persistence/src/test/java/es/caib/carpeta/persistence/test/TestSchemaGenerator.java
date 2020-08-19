package es.caib.carpeta.persistence.test;

import org.junit.Test;

import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Test per realitzar la generació de l'schema de base de dades.
 */
public class TestSchemaGenerator {
	
	
	
	public static void main(String[] args) {
	    // NO FUNCIONA DES D'ECLIPSE
		new TestSchemaGenerator().testCreateSchema();
	}
	
	

    /**
     * A partir del persistence.xml definit a test genera l'schema de base de dades.
     */
    @Test
    public void testCreateSchema() {
        // Les propietats per la generació es poden indicar dins el persistence.xml o es poden
        // passar dins el Map.
        
        Map<String, String> map = new HashMap<String,String>();
        
        
         // Configuració per generar els scripts de creació de la base de dades -->
        map.put("javax.persistence.schema-generation.scripts.action","drop-and-create");
        map.put("javax.persistence.schema-generation.scripts.create-target", "create_db.sql");
        map.put("javax.persistence.schema-generation.scripts.drop-target", "drop_db.sql");

        /*
        Propietats específiques de Hibernate
        https://docs.jboss.org/hibernate/orm/5.3/userguide/html_single/Hibernate_User_Guide.html#configurations
        */
        map.put("hibernate.show_sql", "true");
        map.put("hibernate.format_sql", "true");
        map.put("hibernate.hbm2ddl.delimiter",";");
        //map.put("provider", "org.hibernate.jpa.HibernatePersistenceProvider");

        /*
        Si no indicam una connexió de bdd (per la generació no cal), Hibernate necessita que indiquem
        el dialecte que emprarà per la generació.
        Postgresql:    org.hibernate.dialect.PostgreSQL95Dialect
        Oracle:        org.hibernate.dialect.Oracle12cDialect
        */
        
        
        //String dialect = "org.hibernate.dialect.PostgreSQL95Dialect";
        String dialect = "org.hibernate.dialect.Oracle12cDialect";
        
       
        map.put("hibernate.dialect", dialect);
        //map.put(""hibernate.query.substitutions","true 1, false 0");

        
        Persistence.generateSchema("carpetaPULocal", map);
    }
}
