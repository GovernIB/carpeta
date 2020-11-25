package es.caib.carpeta.hibernate;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.Constants;

public class CustomHibernatePersistenceProvider extends HibernatePersistenceProvider {
	
	private static final Logger log = LoggerFactory.getLogger(Configuracio.class);
	
	@Override
	public EntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo info, Map properties) {
		
		Map carpetaProperties = new HashMap<String,String>();
		
		if(!properties.isEmpty()) {
			carpetaProperties.putAll(properties);
		}
		
		Properties fitxerProperties = Configuracio.getSystemAndFileProperties();
		if(fitxerProperties != null) {
			fitxerProperties.forEach((k,v) -> {
	        	if (k.toString().startsWith(Constants.CARPETA_PROPERTY_HIBERNATE)) {
	        		carpetaProperties.put(k.toString().replace(Constants.CARPETA_PROPERTY_BASE,""), v.toString());
	        	}
	        });
		}
		
		if (Configuracio.isDesenvolupament()) {
			carpetaProperties.forEach((k,v) -> {
	        	log.info("HibernateProperties: " + k + ", Value : " + v);
	        });
		}
		
		return super.createContainerEntityManagerFactory(info, carpetaProperties);
	}
	
}
