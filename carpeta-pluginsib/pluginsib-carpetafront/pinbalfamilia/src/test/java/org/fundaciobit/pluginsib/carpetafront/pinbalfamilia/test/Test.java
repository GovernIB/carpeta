package org.fundaciobit.pluginsib.carpetafront.pinbalfamilia.test;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.fundaciobit.pluginsib.carpetafront.pinbalfamilia.PinbalFamiliaCarpetaFrontPlugin;
import org.fundaciobit.pluginsib.carpetafront.pinbalfamilia.PinbalFamiliaCarpetaFrontPlugin.DatosFamilia;

import es.caib.carpeta.pluginsib.carpetafront.api.UserData;

public class Test {

	public static void main(String[] args) {
		
		String base = "es.caib.carpeta.";
		Properties prop = new Properties();
		
		try {
			
			BasicConfigurator.configure();
			
			prop.load(new FileInputStream("plugin.properties"));
			
			PinbalFamiliaCarpetaFrontPlugin pinbal = new PinbalFamiliaCarpetaFrontPlugin(base, prop);
			
			UserData userData = new UserData();
			
			String absolutePath = "";
			
			DatosFamilia datos = pinbal.cridadaRest(userData, absolutePath);
			
			if(datos.getError() != null && !datos.getError().isEmpty()) {
				System.err.println("Error:" + datos.getError());
			}
			
			System.out.println("FINAL");
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
