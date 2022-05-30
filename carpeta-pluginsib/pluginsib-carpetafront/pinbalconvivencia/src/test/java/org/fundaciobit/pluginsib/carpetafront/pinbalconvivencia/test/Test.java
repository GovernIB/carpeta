package org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.test;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.PinbalConvivenciaCarpetaFrontPlugin;
import org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.resposta.DatosConvivencia;

import es.caib.carpeta.pluginsib.carpetafront.api.UserData;

public class Test {

	public static void main(String[] args) {
		
		String base = "es.caib.carpeta.";
		Properties prop = new Properties();
		
		try {
			
			BasicConfigurator.configure();
			
			prop.load(new FileInputStream("plugin.properties"));
			
			PinbalConvivenciaCarpetaFrontPlugin pinbalConvivencia = new PinbalConvivenciaCarpetaFrontPlugin(base, prop);
			
			UserData userData = new UserData();
			
			String absolutePath = "";
			
			DatosConvivencia datosConvivencia = pinbalConvivencia.cridadaRest(userData, null, absolutePath);
			
			if(datosConvivencia.getError() != null && !datosConvivencia.getError().isEmpty()) {
				System.err.println("Error:" + datosConvivencia.getError());
			}else {
				System.out.println("CODIGO: " + datosConvivencia.getCodigo());
			}
			
			System.out.println("FINAL");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
