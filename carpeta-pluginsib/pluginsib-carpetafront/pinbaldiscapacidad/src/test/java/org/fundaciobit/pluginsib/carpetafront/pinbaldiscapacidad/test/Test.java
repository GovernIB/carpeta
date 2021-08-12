package org.fundaciobit.pluginsib.carpetafront.pinbaldiscapacidad.test;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.fundaciobit.pluginsib.carpetafront.pinbaldiscapacidad.PinbalDiscapacidadCarpetaFrontPlugin;
import org.fundaciobit.pluginsib.carpetafront.pinbaldiscapacidad.PinbalDiscapacidadCarpetaFrontPlugin.DatosDiscapacidad;

import es.caib.carpeta.pluginsib.carpetafront.api.UserData;

public class Test {

	public static void main(String[] args) {
		
		String base = "es.caib.carpeta.";
		Properties prop = new Properties();
		
		try {
			
			BasicConfigurator.configure();
			
			prop.load(new FileInputStream("plugin.properties"));
			
			PinbalDiscapacidadCarpetaFrontPlugin pinbal = new PinbalDiscapacidadCarpetaFrontPlugin(base, prop);
			
			UserData userData = new UserData();
			
			String absolutePath = "";
			
			DatosDiscapacidad datos = pinbal.cridadaRest(userData, absolutePath);
			
			if(datos.getError() != null && !datos.getError().isEmpty()) {
				System.err.println("Error:" + datos.getError());
			}
			
			System.out.println("FINAL");
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
