package org.fundaciobit.pluginsib.carpetafront.pinbalcontrataciones.test;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.fundaciobit.pluginsib.carpetafront.pinbalcontrataciones.PinbalContratacionesCarpetaFrontPlugin;
import org.fundaciobit.pluginsib.carpetafront.pinbalcontrataciones.PinbalContratacionesCarpetaFrontPlugin.DatosContrataciones;

import es.caib.carpeta.pluginsib.carpetafront.api.UserData;

public class Test {

	public static void main(String[] args) {
		
		String base = "es.caib.carpeta.";
		Properties prop = new Properties();
		
		try {
			
			BasicConfigurator.configure();
			
			prop.load(new FileInputStream("plugin.properties"));
			
			PinbalContratacionesCarpetaFrontPlugin pinbal = new PinbalContratacionesCarpetaFrontPlugin(base, prop);
			
			UserData userData = new UserData();
			
			String absolutePath = "";
			
			DatosContrataciones datos = pinbal.cridadaRest(userData, absolutePath);
			
			if(datos.getError() != null && !datos.getError().isEmpty()) {
				System.err.println("Error:" + datos.getError());
			}else {
				System.out.println("Nombre: " + datos.getNombre() + " " + datos.getApellido1());
				System.out.println("Código estado:" + datos.getCodigo() + " " + datos.getDescripcion());
			}
			
			System.out.println("FINAL");
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
