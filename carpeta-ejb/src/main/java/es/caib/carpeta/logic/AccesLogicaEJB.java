package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;

import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.carpeta.ejb.AccesEJB;
import es.caib.carpeta.model.entity.Acces;
import es.caib.carpeta.model.fields.AccesQueryPath;
import es.caib.carpeta.persistence.AccesJPA;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez Date: 16/12/2020
 * @author anadal Date: 11/08/2025
 */
@Stateless
public class AccesLogicaEJB extends AccesEJB implements AccesLogicaService {

    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
    protected EntitatLogicaService entitatLogicaEjb;

    @PermitAll
    @Override
    public void crearAcces(UsuarioClave usuarioClave, @NotNull int tipus, long entitatID, Long pluginID,
            Timestamp dataDarrerAcces, String idioma, String ipAddress, boolean resultat, String idSessio)
            throws I18NException {

        AccesJPA accesJPA = new AccesJPA();

        if (usuarioClave != null) {
            // Accés a un plugin PUBLIC de forma no autenticada
            accesJPA.setNom(usuarioClave.getNombre());
            accesJPA.setLlinatges(usuarioClave.getApellido1() + " " + usuarioClave.getApellido2());
            accesJPA.setNif(usuarioClave.getNif());
            accesJPA.setQaa(usuarioClave.getQaa());

            // S'ha d'arreglar a https://github.com/GovernIB/carpeta/issues/308
            // Això està be ????
            accesJPA.setMetodeAutenticacio(usuarioClave.getMetodoAutentificacion());
            accesJPA.setProveidorIdentitat(usuarioClave.getProveedorDeIdentidad());
        }

        accesJPA.setIp(ipAddress);
        accesJPA.setDataAcces(dataDarrerAcces);
        accesJPA.setIdioma(idioma);
        accesJPA.setResultat(resultat);
        accesJPA.setIdsessio(idSessio);

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
    public List<Acces> findBetweenDates(Date inici, Date fi, String codiEntitat) throws I18NException {

        /*
         * String sentencia = "select a from AccesJPA a " +
         * "where a.dataAcces between :dataInici and :dataFi " +
         * "and a.entitat.codi = :codiEntitat " + "order by a.dataAcces desc";
         * 
         * TypedQuery<AccesJPA> query = getEntityManager().createQuery(sentencia,
         * AccesJPA.class); query.setParameter("dataInici", inici);
         * query.setParameter("dataFi", fi); query.setParameter("codiEntitat",
         * codiEntitat);
         */

        Where w1 = DATAACCES.between(new Timestamp(inici.getTime()), new Timestamp(fi.getTime()));
        AccesQueryPath aqp = new AccesQueryPath();
        Where w2 = aqp.ENTITAT().CODI().equal(codiEntitat);
        Where w = Where.AND(w1, w2);
        OrderBy order = new OrderBy(DATAACCES, OrderType.DESC);
        return select(w, order);

        // return query.getResultList();

    }

    /* Retorna el darrer acces registrat de l'usuari */
    @Override
    public Acces getLastAcces(String nif) throws I18NException {
        final int nAccessos = 2; // Hem de recuperar ara i el darrer Login
        final Where w1 = NIF.equal(nif);
        final Where w2 = PLUGINID.isNull(); // Si plugin==null significa que es de tipus Login
        OrderBy order = new OrderBy(DATAACCES, OrderType.DESC);
        Where w = Where.AND(w1, w2);
        List<Acces> accessos = select(w, 0, nAccessos, order);
        if (accessos==null || accessos.isEmpty()) {
            return null; // Si no hi ha accés, retornem null
        } else if (accessos.size() == 1) {
            return accessos.get(0); // Si només hi ha un accés, el retornam;
        } else {
            // Si hi ha més d'un accés, retornam el segon (el penúltim)
            // Perquè el primer és l'actual i el segon és el darrer Login
            return accessos.get(1);
        }
    }

    @Override
    public Acces getLastAccesByEntity(String nif, long entityId) throws I18NException {
        final int nAccessos = 2; // Per defecte, només volem l'últim accés
        final Where w1 = NIF.equal(nif);
        final Where w2 = ENTITATID.equal(entityId);
        final Where w3 = PLUGINID.isNull(); // Si plugin==null significa que es de tipus Login
        OrderBy order = new OrderBy(DATAACCES, OrderType.DESC);
        Where w = Where.AND(w1, w2, w3);
        List<Acces> accessos = select(w, 0, nAccessos, order);
        if (accessos==null || accessos.isEmpty()) {
            return null; // Si no hi ha accés, retornem null
        } else if (accessos.size() == 1) {
            return accessos.get(0); // Si només hi ha un accés, el retornam;
        } else {
            // Si hi ha més d'un accés, retornam el segon (el penúltim)
            // Perquè el primer és l'actual i el segon és el darrer Login
            return accessos.get(1);
        }
    }

}
