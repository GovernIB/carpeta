package org.fundaciobit.pluginsib.carpetafront.pinbalayudasubvenciones.test;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.fundaciobit.pluginsib.carpetafront.pinbalayudasubvenciones.PinbalAyudaSubvencionesCarpetaFrontPlugin;
import org.fundaciobit.pluginsib.carpetafront.pinbalayudasubvenciones.PinbalAyudaSubvencionesCarpetaFrontPlugin.DatosAyudaSubvenciones;

import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
import es.caib.pinbal.client.recobriment.model.ScspJustificante;

public class Test {

	public static void main(String[] args) {
		
		String base = "es.caib.carpeta.";
		Properties prop = new Properties();
		
		try {
			
			BasicConfigurator.configure();
			
			prop.load(new FileInputStream("plugin.properties"));
			
			PinbalAyudaSubvencionesCarpetaFrontPlugin pinbalAyuda = new PinbalAyudaSubvencionesCarpetaFrontPlugin(base, prop);
			
			UserData userData = new UserData();
			
			String absolutePath = "";
			
			DatosAyudaSubvenciones datosAyuda = pinbalAyuda.cridadaRest(userData, absolutePath);
			
			if(datosAyuda.getError() != null && !datosAyuda.getError().isEmpty()) {
				System.err.println("Error:" + datosAyuda.getError());
			}else {
				String idPeticion = datosAyuda.getIdPeticion();
				System.out.println("CODIGO: " + datosAyuda.getCodigo());
				System.out.println("PETICION: " + idPeticion);
				
				ScspJustificante justificante = pinbalAyuda.obtenirJustificant(idPeticion);
				
				if(justificante != null) {
					System.out.println("Nombre: " + justificante.getNom());
					System.out.println("Long. : " + justificante.getLongitud());
				}else {
					System.err.println("Justificante IS NULL");
				}
				
			}
			
			System.out.println("FINAL");
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
