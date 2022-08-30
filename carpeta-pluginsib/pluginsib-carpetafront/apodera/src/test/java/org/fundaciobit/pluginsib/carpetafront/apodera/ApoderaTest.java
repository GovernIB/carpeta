package org.fundaciobit.pluginsib.carpetafront.apodera;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.fundaciobit.pluginsib.carpetafront.apodera.api.*;
import org.junit.Test;

/**
 * 
 * @author anadal
 *
 */
public class ApoderaTest {

    @Test
    public void extecuteTest() {
        main(null);
    }

    public static void main(String[] args) {

        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("connexio.properties"));

            String nifPoderdante = prop.getProperty("nif.poderdante");
            String nifApoderado = prop.getProperty("nif.apoderado");

            ApoderaCarpetaFrontPlugin plugin = new ApoderaCarpetaFrontPlugin("es.caib.carpeta.", prop);

            System.out.println("Nif Apoderado: " + nifApoderado);
            System.out.println("Nif Poderdante: " + nifPoderdante);
            // Cridada
            ConsultaApoderamientosResponse response = plugin.consultaInterna(nifPoderdante,
                    nifApoderado);


            List<DatosApoderamientoType> apoderamientos = response.getListaApoderamientos();
            System.out.println("apoderamientos: " + apoderamientos.size());

            if (apoderamientos.size() == 0) {
                System.err.println("No hi ha apoderaments per aquest usuari ...");
            } else {

                int i = 1;
                for (DatosApoderamientoType d : apoderamientos) {

                    System.out.println(i + ".- Common Info=>  Estat:" + d.getEstado()
                            + "\tcodiApoderaEXT:" + d.getCodApoderamientoEXT() + "\tcodiApoderaINT:"
                            + d.getCodApoderamientoINT());

                    TipoApoderamiento ta = TipoApoderamiento.getTipoApoderamiento(
                            d.getTipoApoderamiento().getTipoApod(),
                            d.getTipoApoderamiento().getSubTipoApod());

                    String ts = "Tipo:" + d.getTipoApoderamiento().getTipoApod() + " | Subtipo:"
                            + d.getTipoApoderamiento().getSubTipoApod();

                    if (ta == null) {
                        System.out.println(i + ".- Tipo Apoderamiento => DESCONEGUT (" + ts + ")");
                    } else {
                        System.out.println(i + ".- Tipo Apoderamiento => " + ta.getDescripcion()
                                + " (" + ts + ")");
                    }

                    DatosApoderadoCompletoType apo = d.getDatosApoderado();
                    if (apo.getPersonaFisica() != null) {
                        PersonaFisicaType pf = apo.getPersonaFisica();
                        String titol = i + ".- Apoderat Perso. Fisica => ";

                        System.out.println(toStringPersonaFisica(pf, titol));
                    }
                    if (apo.getPersonaJuridica() != null) {
                        PersonaJuridicaType pf = apo.getPersonaJuridica();
                        String titol = i + ".- Apoderat Perso. Juridica => ";

                        System.out.println(toStringPersonaJuridica(pf, titol));
                    }

                    DatosPoderdanteCompletoType poderdante = d.getDatosPoderdante();
                    if (poderdante.getPersonaFisica() != null) {
                        System.out.println(toStringPersonaFisica(poderdante.getPersonaFisica(),
                                i + ".- Poderdante Perso. Fisica => "));
                    }

                    if (poderdante.getPersonaJuridica() != null) {
                        System.out.println(toStringPersonaJuridica(poderdante.getPersonaJuridica(),
                                i + ".- Poderdante Perso. Juridica => "));
                    }

                    if (poderdante.getRepresentante() != null) {
                        System.out.println(toStringPersonaFisica(poderdante.getRepresentante(),
                                i + ".- Poderdante Representante => "));
                    }
                    
                    
                    
                    

                    if (d.getPeriodoVigencia() != null) {
                        System.out.println(
                                i + ".- Vigencia => " + d.getPeriodoVigencia().getFechaInicio()
                                        + " - " + d.getPeriodoVigencia().getFechaFin());
                    }
                    
                    
                    d.getDatosPoderdante().

                    // d.get
                    // System.out.println(i + ".- Common Info=>
                    i++;
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    protected static String toStringPersonaJuridica(PersonaJuridicaType pf, String titol) {
        return titol + " NIF:" + pf.getNif() + "\tN:" + pf.getRazonSocial() + "\tE:" + pf.getEmail()
                + "\tD:" + pf.getDomicilio();
    }

    protected static String toStringPersonaFisica(PersonaFisicaType pf, String titol) {
        return titol + " NIF:" + pf.getNifNie() + "\tN:" + pf.getNombre() + "\tA1:"
                + pf.getApellido1() + "\tA2:" + pf.getApellido2() + "\tE:" + pf.getEmail();
    }
}
