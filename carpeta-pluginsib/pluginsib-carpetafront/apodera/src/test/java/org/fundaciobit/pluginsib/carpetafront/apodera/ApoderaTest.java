package org.fundaciobit.pluginsib.carpetafront.apodera;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBElement;


import org.fundaciobit.pluginsib.carpetafront.apodera.api.ConsultaApoderamientosResponse;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.DatosApoderadoCompletoType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.DatosApoderadoType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.DatosApoderamientoType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.DatosAuditoriaType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.DatosConsultaApoderamientoType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.DatosConsultaType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.DatosPoderdanteCompletoType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.ObjectFactory;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.Organismo;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.OrganismoType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.PersonaFisicaType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.PersonaJuridicaType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.PeticionConsulta;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.TipoApoderamientoType;

/**
 * 
 * @author anadal
 *
 */
public class ApoderaTest {

  public static void main(String[] args) {
    
    
    try {
      Properties prop = new Properties();
      prop.load(new FileInputStream("connexio.properties"));
      
      
      String endPoint = prop.getProperty("endpoint");
        

      String codAplicacion = prop.getProperty("codiApp");


       // # CERTIFICATE Token
        
       String auth_ks_Path = prop.getProperty("authorization.ks.path");
       String auth_ks_Type = prop.getProperty("authorization.ks.type");
       String auth_ks_Password = prop.getProperty("authorization.ks.password");
       String auth_ks_Alias = prop.getProperty("authorization.ks.cert.alias");
       String auth_ks_Cert_Password = prop.getProperty("authorization.ks.cert.password");

       String organisme_dir3 = prop.getProperty("organisme.dir3");
       String organisme_denominacio = prop.getProperty("organisme.denominacio");
       
       
       String nifApoderado = "43096845C";
        
        
        ApoderaClient api = new ApoderaClient(endPoint, auth_ks_Path, auth_ks_Type,
            auth_ks_Password, auth_ks_Alias, auth_ks_Cert_Password);
        
        DatosAuditoriaType datosAuditoriaType = new DatosAuditoriaType();
        datosAuditoriaType.setCodAplicacion(codAplicacion);
        
        SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
        
        datosAuditoriaType.setTimestamp(Long.parseLong(SDF.format(new Date())));
        
        DatosApoderadoType datosApoderado = new DatosApoderadoType();
        datosApoderado.setNifNieApoderado(nifApoderado); //"43096845C");

        TipoApoderamientoType tipoApoderamiento = null;

        tipoApoderamiento = new TipoApoderamientoType();

        /*
        final TipoApoderamiento t = TipoApoderamiento.GENERAL; // TipoApoderamiento.TODAS_LAS_ADMINISTRACIONES_PUBLICAS; //TipoApoderamiento.PROCEDIMIENTOS_Y_O_TRAMITES;
        tipoApoderamiento.setTipoApod(t.getTipo());
        tipoApoderamiento.setSubTipoApod(t.getSubtipo());
        */

        OrganismoType organismoType = new OrganismoType();

        organismoType.setCodOrganismo(organisme_dir3); 
        organismoType.setDenomOrganismo(organisme_denominacio);

        //organismoType.setCodOrganismo("A04027052"); 
        //organismoType.setDenomOrganismo("Gobierno de las Islas Baleares - Fundación Bit"); //"Fondo de Garantia Salarial");

        Organismo organismo = new Organismo();
        organismo.getOrganismo().add(organismoType);
        ObjectFactory factory = new ObjectFactory();
        JAXBElement<Organismo> jaxbelementOrganismo =  factory.createTipoApoderamientoTypeListaOrganismos(organismo);
        tipoApoderamiento.setListaOrganismos(jaxbelementOrganismo);
        
        DatosConsultaApoderamientoType dcat = new DatosConsultaApoderamientoType();
        dcat.setDatosApoderado(datosApoderado);
        dcat.setTipoApoderamiento(tipoApoderamiento);
        
        //dcat.setCodApoderamientoEXT(0L);
        //dcat.setCodApoderamientoINT(1147L);

        
        DatosConsultaType datosConsultaType = new DatosConsultaType();
        datosConsultaType.setTipoConsulta(false); // false => simple 0 || true => completa 1
        datosConsultaType.setDatosConsultaApoderamiento(dcat);

        PeticionConsulta peticio = new PeticionConsulta();
        peticio.setDatosAuditoriaType(datosAuditoriaType);
        peticio.setDatosConsultaType(datosConsultaType);

        // Cridada
        ConsultaApoderamientosResponse response = api.consulta(peticio);
        
        List<DatosApoderamientoType> apoderamientos = response.getListaApoderamientos();
        
        
        if (apoderamientos.size() == 0) {
           System.err.println("No hi ha apoderaments per aquest usuari ...");
        } else {
        
        int i = 1;
        for (DatosApoderamientoType d : apoderamientos) {

           System.out.println(i + ".- Common Info=>  Estat:" + d.getEstado() 
               + "\tcodiApoderaEXT:" + d.getCodApoderamientoEXT() + "\tcodiApoderaINT:" + d.getCodApoderamientoINT() );
           
           TipoApoderamiento ta = TipoApoderamiento.getTipoApoderamiento(d.getTipoApoderamiento().getTipoApod(),
               d.getTipoApoderamiento().getSubTipoApod() );
           
           String ts = "Tipo:" + d.getTipoApoderamiento().getTipoApod()
               + " | Subtipo:" + d.getTipoApoderamiento().getSubTipoApod(); 
           
           if (ta == null) {
             System.out.println(i + ".- Tipo Apoderamiento => DESCONEGUT (" + ts + ")");
           } else {
             System.out.println(i + ".- Tipo Apoderamiento => " + ta.getDescripcion() + " (" + ts + ")");
           }
           
           DatosApoderadoCompletoType apo = d.getDatosApoderado();
           if (apo.getPersonaFisica() != null) {
             PersonaFisicaType pf = apo.getPersonaFisica();
             String titol = i + ".- Apoderat Perso. Fisica => ";
             
             System.out.println(toStringPersonaFisica(pf, titol) );
           }
           if (apo.getPersonaJuridica() != null) {
             PersonaJuridicaType pf = apo.getPersonaJuridica();
             String titol = i + ".- Apoderat Perso. Juridica => ";
             
             System.out.println(toStringPersonaJuridica(pf, titol) );
           }
           
           DatosPoderdanteCompletoType poderdante = d.getDatosPoderdante();
           if (poderdante.getPersonaFisica() != null) {
             System.out.println(toStringPersonaFisica(poderdante.getPersonaFisica(), i + ".- Poderdante Perso. Fisica => ") );
           }
           
           if (poderdante.getPersonaJuridica() != null) {
             System.out.println(toStringPersonaJuridica(poderdante.getPersonaJuridica(), i + ".- Poderdante Perso. Juridica => ") );
           }
           
           if (poderdante.getRepresentante() != null) {
             System.out.println(toStringPersonaFisica(poderdante.getRepresentante(), i + ".- Poderdante Representante => ") );
           }

           if (d.getPeriodoVigencia() != null) {
             System.out.println(i + ".- Vigencia => " +  d.getPeriodoVigencia().getFechaInicio() + " - " + d.getPeriodoVigencia().getFechaFin());          
           }
           
           //d.get
           //System.out.println(i + ".- Common Info=>
           i++;
        }
        }
        
        
      
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    
    
    
  }

  protected static String toStringPersonaJuridica(PersonaJuridicaType pf, String titol) {
    return titol + " NIF:" + pf.getNif() + "\tN:" + pf.getRazonSocial()
        + "\tE:" + pf.getEmail() + "\tD:" + pf.getDomicilio();
  }

  protected static String toStringPersonaFisica(PersonaFisicaType pf, String titol) {
    return titol + " NIF:" + pf.getNifNie() + "\tN:" + pf.getNombre() + "\tA1:"
       + pf.getApellido1() + "\tA2:" + pf.getApellido2() + "\tE:" + pf.getEmail();
  }
}
