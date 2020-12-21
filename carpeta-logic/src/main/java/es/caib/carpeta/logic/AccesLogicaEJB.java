package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.carpeta.ejb.AccesEJB;
import es.caib.carpeta.jpa.AccesJPA;
import es.caib.carpeta.jpa.EntitatJPA;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 16/12/2020
 */
@Stateless
public class AccesLogicaEJB extends AccesEJB implements AccesLogicaLocal{

    @EJB(mappedName = EntitatLogicaLocal.JNDI_NAME)
    protected EntitatLogicaLocal entitatLogicaEjb;

    @Override
    public void crearAcces(UsuarioClave usuarioClave,@NotNull int tipus, String codiEntitat, Integer pluginID, Timestamp dataDarrerAcces, String idioma, String ipAddress) throws I18NException {

        AccesJPA accesJPA = new AccesJPA();

        accesJPA.setNom(usuarioClave.getNombre());
        accesJPA.setLlinatges(usuarioClave.getApellido1() +" " + usuarioClave.getApellido2());
        accesJPA.setIp(ipAddress);
        accesJPA.setNif(usuarioClave.getNif());
        accesJPA.setDataDarrerAcces(dataDarrerAcces);
        accesJPA.setResultatAutenticacio(Integer.parseInt(usuarioClave.getQaa()));
        accesJPA.setNivellSeguretat(usuarioClave.getMetodoAutentificacion());
        accesJPA.setProveidorIdentitat("Clave");//TODO este valor no viene informado por LoginIB, tendriamos que hablar con loginIb a ver si pueden enviarnoslo.
        accesJPA.setIdioma(idioma);

        accesJPA.setDataDarrerAcces(new Timestamp(new Date().getTime()));
        accesJPA.setTipus(tipus);

        //Cercam primer la entitat
        EntitatJPA entitatJPA = entitatLogicaEjb.findByCodiDir3(codiEntitat);
        if(entitatJPA!=null) {
            accesJPA.setEntitatID(entitatJPA.getEntitatID());
        }

        accesJPA.setPluginID(pluginID);


        create(accesJPA);


    }
    
    /* Llistat de accesos entre dues dates ordenat per data descendent */
    @Override
    public List<AccesJPA> findBetweenDates(Date inici, Date fi, long entitat) throws I18NException {
    	
    	String filtreEntitat = (entitat > 0) ? "and a.entitatID = :codiEntitat " : "";
    	String sentencia = "select a from AccesJPA a "
				+ "where a.dataDarrerAcces between :dataInici and :dataFi "
				+ filtreEntitat
				+ "order by a.dataDarrerAcces desc";
    	
    	TypedQuery<AccesJPA> query = getEntityManager().createQuery(
				sentencia, AccesJPA.class);
		query.setParameter("dataInici", inici);
		query.setParameter("dataFi", fi);
		
		System.out.println("sentencia:" + sentencia);
		System.out.println("Inici: " + inici);
		System.out.println("Fi: " + fi);
		System.out.println("Entitat: " + entitat);
		
		if(filtreEntitat != "")
			query.setParameter("codiEntitat", entitat);
		
		return query.getResultList();
    	
    }
}
