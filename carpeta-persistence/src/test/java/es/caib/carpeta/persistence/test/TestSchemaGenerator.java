package es.caib.carpeta.persistence.test;

import org.junit.Test;

import javax.persistence.Persistence;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Test per realitzar la generació de l'schema de base de dades.
 */
public class TestSchemaGenerator {
	
	
	
	public static void main(String[] args) {
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
        
        Persistence.generateSchema("testPU", map);
    }
}
