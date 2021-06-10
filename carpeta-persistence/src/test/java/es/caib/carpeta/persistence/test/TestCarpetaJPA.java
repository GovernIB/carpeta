package es.caib.carpeta.persistence.test;

import java.util.List;
import java.util.Properties;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.LongField;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.junit.Test;

import es.caib.carpeta.persistence.CarpetaJPADaoManagers;
import es.caib.carpeta.persistence.PluginJPA;
import es.caib.carpeta.model.CarpetaDaoManager;
import es.caib.carpeta.model.dao.IPluginManager;
import es.caib.carpeta.model.entity.Plugin;

/**
 * 
 * @author anadal
 *
 */
public class TestCarpetaJPA {

    public static final Logger log = Logger.getLogger(TestCarpetaJPA.class);

    @Test
    public static final void main(String[] args) {
        try {
            log.info(">>>>>>>>>>>>  Hello World!");

            // USING GENAPP
            // ============

            Properties prop = new Properties();

            prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            prop.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
            prop.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/carpeta");
            prop.put("javax.persistence.jdbc.user", "carpeta");
            prop.put("javax.persistence.jdbc.password", "carpeta");

            prop.put("hibernate.connection.driver_class", "org.postgresql.Driver");
            // prop.put("javax.persistence.jdbc.url","jdbc:postgresql://192.168.35.151:5432/pinbaladmin");
            prop.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/carpeta");
            prop.put("hibernate.connection.username", "carpeta");
            prop.put("hibernate.connection.password", "carpeta");

            prop.put("hibernate.show_sql", "true");

            EntityManagerFactory emf;

            // Veure persistence.xml
            emf = Persistence.createEntityManagerFactory("carpetaPULocal" /* "carpetaDBStandalone" */, prop);

            EntityManager em = emf.createEntityManager();

            em.setFlushMode(FlushModeType.AUTO);

            EntityTransaction tx = em.getTransaction();

            tx.begin();

            boolean f = false;
            if (f) {
                throw new I18NException("werrrtt");
            }

            CarpetaDaoManager.setDaoManagers(new CarpetaJPADaoManagers(em)); // firmesDaoManagers

            IPluginManager pluginMan = CarpetaDaoManager.getDaoManagers().getPluginManager();

           

            /*
             * select traduccioj0_.traduccioid as traducci1_13_0_,
             * traduccion1_.traducciomapid as traducci1_14_1_, traduccion1_.valor as
             * valor2_14_1_, traduccion1_.idiomaid as idiomaid3_1_ from car_traduccio
             * traduccioj0_ left outer join car_traducciomap traduccion1_ on
             * traduccioj0_.traduccioid=traduccion1_.traducciomapid where
             * traduccioj0_.traduccioid=?
             */
            
            /*
            String hsql = "SELECT " + PluginFields.NOMID.fullName 
             // + ",(SELECT valor FROM car_traducciomap WHERE traducciomapid = car_plugin.nomid and car_traducciomap.idiomaid = 'es') as nom" 
             // + ",(SELECT valor FROM car_traducciomap WHERE traducciomapid = car_plugin.descripcioid and car_traducciomap.idiomaid = 'es') as descripcio"
             + " FROM PluginJPA plugin, TraduccioJPA traduccio, TraduccioMapJPA traduccioMap  "
             + " ORDER BY " + PluginFields.NOMID.fullName + " DESC";
            
            javax.persistence.Query qry = em.createQuery(hsql);
            
            List<Object> list = qry.getResultList();
            for (Object object : list) {
                System.out.println("Object[] => " + object);
            }
            
            */
            
            /*
            SelectTraduccio st = new SelectTraduccio(PluginFields.NOMID, "es");

            List<String> noms = pluginMan.executeQuery(st, null);
            
            for (String nom : noms) {
                System.out.println("NOM[" + nom + "]");
            }
            
            */
            
            // System.out.println(" PATH => " + new PluginQueryPath().NOM().TRADUCCIOID().fullName);
            
            
            

            OrderBy order = new OrderBy("plugin.nom"); // ['ca'].valor .traduccions.valor
            
            List<Plugin> plugins = pluginMan.select(order);

            for (Plugin plugin : plugins) {
                System.out.println("Plugin[" + plugin.getNomID() + "] => "
                        + ((PluginJPA) plugin).getNom().getTraduccio("ca").getValor());
            }
            

            /*
             * IIdiomaManager idioma =
             * CarpetaDaoManager.getDaoManagers().getIdiomaManager();
             * 
             * List<Idioma> llist = idioma.select(new OrderBy(IdiomaFields.IDIOMAID,
             * OrderType.DESC));
             * 
             * for (Idioma idioma2 : llist) { System.out.println("Idoma = " +
             * idioma2.getIdiomaID() + " => " + idioma2.getNom()); }
             * 
             * System.out.println("===");
             * 
             * llist = idioma.select(IdiomaFields.NOM.like("%Cat%"));
             * 
             * for (Idioma idioma2 : llist) { System.out.println("Idoma222 = " +
             * idioma2.getIdiomaID() + " => " + idioma2.getNom()); }
             * 
             */

            tx.commit();
            log.info("<<<<<<<<<<<  Good Bye!");

        } catch (I18NException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static class SelectTraduccio extends Select<String> {

        protected LongField field;

        protected String language;

        public SelectTraduccio(LongField field, String language) {
            this.field = field;
            this.language = language;
        }

        @Override
        public String getSelectString() {

            String hsql = "(SELECT valor " + "FROM TraduccioMapJPA traduccioMap" + " WHERE traducciomapid = " + field.fullName
                    + " and traduccioMap.idiomaid = '" + this.language + "')";
            System.out.println("getFromObject :: SQL => ]" + hsql + "[");
            return hsql;
        }

        @Override
        public String getFromObject(Object obj) throws I18NException {

            System.out.println("getFromObject :: class => " + obj.getClass());

            return (String) obj;
        }

    }

}
