package org.fundaciobit.pluginsib.carpetafront.pinbalpolicia.test;

import java.io.FileInputStream;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.fundaciobit.pluginsib.carpetafront.pinbalpolicia.PinbalPoliciaCarpetaFrontPlugin;
import org.fundaciobit.pluginsib.carpetafront.pinbalpolicia.PinbalPoliciaCarpetaFrontPlugin.DadesPolicia;

import es.caib.carpeta.pluginsib.carpetafront.api.UserData;

public class Test {
	
	public static void main(String[] args) {
		
		String base = "es.caib.carpeta.";
		Properties prop = new Properties();
		
		
		try {
		
			BasicConfigurator.configure();
			
			prop.load(new FileInputStream("plugin.properties"));
			
			PinbalPoliciaCarpetaFrontPlugin pinbalpolicia = new PinbalPoliciaCarpetaFrontPlugin(base, prop);
			
			UserData userData = new UserData();
			Locale locale = new Locale("ca");
			
			DadesPolicia dadesPolicia = pinbalpolicia.cridadaRest(userData, locale);
			
			if(dadesPolicia.getError() != null && !dadesPolicia.getError().isEmpty()) {
				System.err.println("Error:" + dadesPolicia.getError());
			}else {
				System.out.println("NOMBRE: " + dadesPolicia.getDatosTitular().getNombre() + " " + dadesPolicia.getDatosTitular().getApellido1());
			}
			
			System.out.println("FINAL");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		
		
		
	}

}
