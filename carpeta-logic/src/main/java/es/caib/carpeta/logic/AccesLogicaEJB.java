package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.carpeta.ejb.AccesEJB;
import es.caib.carpeta.persistence.AccesJPA;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez Date: 16/12/2020
 */
@Stateless
public class AccesLogicaEJB extends AccesEJB implements AccesLogicaService {

    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
    protected EntitatLogicaService entitatLogicaEjb;

    @Override
    public void crearAcces(UsuarioClave usuarioClave, @NotNull int tipus, long entitatID, Long pluginID,
            Timestamp dataDarrerAcces, String idioma, String ipAddress,boolean resultat ) throws I18NException {

        AccesJPA accesJPA = new AccesJPA();

        accesJPA.setNom(usuarioClave.getNombre());
        accesJPA.setLlinatges(usuarioClave.getApellido1() + " " + usuarioClave.getApellido2());
        accesJPA.setIp(ipAddress);
        accesJPA.setNif(usuarioClave.getNif());
        accesJPA.setDataAcces(dataDarrerAcces);
        accesJPA.setQaa(Integer.parseInt(usuarioClave.getQaa()));

        // S'ha d'arreglar a https://github.com/GovernIB/carpeta/issues/308
        // Això està be ????
        accesJPA.setMetodeAutenticacio(usuarioClave.getMetodoAutentificacion());
        accesJPA.setProveidorIdentitat(usuarioClave.getProveedorDeIdentidad());
        accesJPA.setIdioma(idioma);
        accesJPA.setResultat(resultat);

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

        String sentencia = "select a from AccesJPA a " + "where a.dataAcces between :dataInici and :dataFi "
                + "and a.entitat.codi = :codiEntitat " + "order by a.dataAcces desc";

        TypedQuery<AccesJPA> query = getEntityManager().createQuery(sentencia, AccesJPA.class);
        query.setParameter("dataInici", inici);
        query.setParameter("dataFi", fi);
        query.setParameter("codiEntitat", codiEntitat);
        
/*     XYZ ZZZ Canviar per aquest codi
        Where w1 = DATAACCES.between(new TimeStamp(inici.getTime()), new TimeStamp(fi.getTime()))
        AccesQueryPath aqp = new AccesQueryPath();
        Where w2 = aqp.ENTITAT().CODI().equal(codiEntitat);
        Where w = Where.AND(w1,w2);
        OrderBy order = new OrderBy(DATAACCES, OrderType.DESC);
        return select(w,order);
*/ 
        return query.getResultList();

    }
}
