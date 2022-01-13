package org.fundaciobit.pluginsib.carpetafront.pinbalmatricula.test;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.fundaciobit.pluginsib.carpetafront.pinbalmatricula.PinbalMatriculaCarpetaFrontPlugin;
import org.fundaciobit.pluginsib.carpetafront.pinbalmatricula.PinbalMatriculaCarpetaFrontPlugin.DatosMatricula;

import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
import es.caib.pinbal.client.recobriment.model.ScspJustificante;

import javax.servlet.http.HttpServletRequest;

public class Test {


	public static void main(String[] args) {
		
		String base = "es.caib.carpeta.";
		Properties prop = new Properties();
		
		try {

			BasicConfigurator.configure();
			
			prop.load(new FileInputStream("plugin.properties"));
			
			PinbalMatriculaCarpetaFrontPlugin pinbalMatricula = new PinbalMatriculaCarpetaFrontPlugin(base, prop);
			
			UserData userData = new UserData();
			
			String absolutePath = "";

			HttpServletRequest request = null;
			
			DatosMatricula datosMatricula = pinbalMatricula.cridadaRest(userData, absolutePath, request);
			
			if(datosMatricula.getError() != null && !datosMatricula.getError().isEmpty()) {
				System.err.println("Error:" + datosMatricula.getError());
			}else {
//				String idPeticion = datosMatricula.getIdPeticion();
				System.out.println("RESPUESTA: " + datosMatricula.getCodRespuesta());
//				System.out.println("PETICION: " + idPeticion);
				
//				ScspJustificante justificante = pinbalMatricula.obtenirJustificant(idPeticion);
				
//				if(justificante != null) {
//					System.out.println("Nombre: " + justificante.getNom());
//					System.out.println("Long. : " + justificante.getLongitud());
//				}else {
//					System.err.println("Justificante IS NULL");
//				}
				
			}
			
			System.out.println("FINAL");
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
