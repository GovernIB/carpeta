package org.fundaciobit.pluginsib.carpetafront.pinbalmatricula.test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.fundaciobit.pluginsib.carpetafront.pinbalmatricula.PinbalMatriculaCarpetaFrontPlugin;
import org.fundaciobit.pluginsib.carpetafront.pinbalmatricula.PinbalMatriculaCarpetaFrontPlugin.DatosMatricula;

import es.caib.carpeta.pluginsib.carpetafront.api.UserData;

/**
 * 
 * @author anadal
 *
 */
public class Test {

    public static void main(String[] args) {

        String base = "es.caib.carpeta.";
        Properties prop = new Properties();

        Properties test = new Properties();
        try {

            BasicConfigurator.configure();

            prop.load(new InputStreamReader(new FileInputStream("plugin.properties"), Charset.forName("UTF-8")));

            PinbalMatriculaCarpetaFrontPlugin pinbalMatricula = new PinbalMatriculaCarpetaFrontPlugin(base, prop);

            test.load(new InputStreamReader(new FileInputStream("test_fill_sense_dni.properties"), Charset.forName("UTF-8")));

            UserData userData = new UserData();
            userData.setName(test.getProperty("userData.name"));
            userData.setSurname1(test.getProperty("userData.surname1"));
            userData.setSurname2(test.getProperty("userData.surname2"));
            userData.setAdministrationID(test.getProperty("userData.administrationID"));

            String formRadioOpcio = test.getProperty("formRadioOpcio");
            String formNomFillSenseDNI = test.getProperty("formNomFillSenseDNI");
            String formPrimerLlinatgeFillSenseDNI = test.getProperty("formPrimerLlinatgeFillSenseDNI");
            String formSegonLlinatgeFillSenseDNI = test.getProperty("formSegonLlinatgeFillSenseDNI");
            String formDataNaixementFillSenseDNI = test.getProperty("formDataNaixementFillSenseDNI");
            String formDocumentFillAmbDNI = test.getProperty("formDocumentFillAmbDNI");
            String formTipusDocumentFillAmbDNI = test.getProperty("formTipusDocumentFillAmbDNI");

            Date dataNaixement = null;
            if (formDataNaixementFillSenseDNI != null && !formDataNaixementFillSenseDNI.equals("")) {
                dataNaixement = PinbalMatriculaCarpetaFrontPlugin.sdfOutput.parse(formDataNaixementFillSenseDNI);
            }

            DatosMatricula datosMatricula = pinbalMatricula.cridadaRestDirecta(userData, formRadioOpcio,
                    formNomFillSenseDNI, formPrimerLlinatgeFillSenseDNI, formSegonLlinatgeFillSenseDNI, dataNaixement,
                    formDocumentFillAmbDNI, formTipusDocumentFillAmbDNI);

            if (datosMatricula.getError() != null && !datosMatricula.getError().isEmpty()) {
                System.err.println("Error:" + datosMatricula.getError());
            } else {
                //				String idPeticion = datosMatricula.getIdPeticion();
                System.out.println("getCodRespuesta: " + datosMatricula.getCodRespuesta());
                System.out.println("getCursoMatriculaFutura: " + datosMatricula.getCursoMatriculaFutura());
                System.out.println("getCursoMatriculaVigente: " + datosMatricula.getCursoMatriculaVigente());
                System.out.println("getDescRespuesta: " + datosMatricula.getDescRespuesta());
                System.out.println("getFechaProceso: " + datosMatricula.getFechaProceso());
                System.out.println("getError: " + datosMatricula.getError());
                System.out.println("getAlumno().getNombre(): " + datosMatricula.getAlumno().getNombre());
                System.out.println("getAlumno().getApellido1(): " + datosMatricula.getAlumno().getApellido1());
                System.out.println("getAlumno().getApellido2(): " + datosMatricula.getAlumno().getApellido2());
                System.out.println("getAlumno().getIdTitular(): " + datosMatricula.getAlumno().getIdTitular());
                System.out.println("getAlumno().getFechaNacimiento(): " + datosMatricula.getAlumno().getFechaNacimiento());

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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
