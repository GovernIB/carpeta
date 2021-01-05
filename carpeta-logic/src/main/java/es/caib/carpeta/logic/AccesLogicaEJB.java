package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.carpeta.ejb.AccesEJB;
import es.caib.carpeta.jpa.AccesJPA;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez Date: 16/12/2020
 */
@Stateless
public class AccesLogicaEJB extends AccesEJB implements AccesLogicaLocal {

    @EJB(mappedName = EntitatLogicaLocal.JNDI_NAME)
    protected EntitatLogicaLocal entitatLogicaEjb;

    @Override
    public void crearAcces(UsuarioClave usuarioClave, @NotNull int tipus, long entitatID, Long pluginID,
            Timestamp dataDarrerAcces, String idioma, String ipAddress) throws I18NException {

        AccesJPA accesJPA = new AccesJPA();

        accesJPA.setNom(usuarioClave.getNombre());
        accesJPA.setLlinatges(usuarioClave.getApellido1() + " " + usuarioClave.getApellido2());
        accesJPA.setIp(ipAddress);
        accesJPA.setNif(usuarioClave.getNif());
        accesJPA.setDataDarrerAcces(dataDarrerAcces);
        // XYZ ZZZ   Això està be ????
        accesJPA.setResultatAutenticacio(Integer.parseInt(usuarioClave.getQaa()));
        // XYZ ZZZ   Això està be ????
        accesJPA.setNivellSeguretat(usuarioClave.getMetodoAutentificacion());
        accesJPA.setProveidorIdentitat(usuarioClave.getProveedorDeIdentidad());
        accesJPA.setIdioma(idioma);

        accesJPA.setDataDarrerAcces(new Timestamp(new Date().getTime()));
        accesJPA.setTipus(tipus);

        // Cercam primer la entitat
        // EntitatJPA entitatJPA = entitatLogicaEjb.findByCodiDir3(codiEntitat);
        // if(entitatJPA!=null) {
        accesJPA.setEntitatID(entitatID);
        // }

        accesJPA.setPluginID(pluginID);

        create(accesJPA);

    }

    /* Llistat de accesos entre dues dates ordenat per data descendent */
    @Override
    public List<AccesJPA> findBetweenDates(Date inici, Date fi, String codiEntitat) throws I18NException {

        String sentencia = "select a from AccesJPA a " + "where a.dataDarrerAcces between :dataInici and :dataFi "
                + "and a.entitat.codi = :codiEntitat " + "order by a.dataDarrerAcces desc";

        TypedQuery<AccesJPA> query = getEntityManager().createQuery(sentencia, AccesJPA.class);
        query.setParameter("dataInici", inici);
        query.setParameter("dataFi", fi);
        query.setParameter("codiEntitat", codiEntitat);

        return query.getResultList();

    }
}
