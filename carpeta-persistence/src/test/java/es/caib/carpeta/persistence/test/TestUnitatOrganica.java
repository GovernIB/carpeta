package es.caib.carpeta.persistence.test;

import es.caib.carpeta.persistence.EstatPublicacio;
import es.caib.carpeta.persistence.UnitatOrganica;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Status;
import javax.transaction.UserTransaction;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

/**
 * Realitza tests de persistència i validació damunt Unitats Orgàniques.
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class TestUnitatOrganica {

    /**
     * Crea l'arxiu de deploy que es desplegarà sobre JBoss per fer els tests.
     * @return arxiu desplegable.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackages(true, "es.caib.carpeta.persistence")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/arquillian-persistence.xml", "META-INF/persistence.xml");
        //System.out.println(jar.toString(true));
        return jar;
    }

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction utx;

    /**
     * Abans de cada test s'inciarà una transacció.
     * @throws Exception Error durant l'inici de la transacció
     */
    @Before
    public void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }

    /**
     * Finalitzsació d'una transacció. Es farà un commit, o un rollback si la transacció s'ha marcat com a rollbackonly
     * @throws Exception Error durant el final de la transacció.
     */
    @After
    public void endTransaction() throws Exception {
        if (utx.getStatus() == Status.STATUS_MARKED_ROLLBACK) {
            utx.rollback();
        } else {
            utx.commit();
        }
        em.clear();
    }

    /**
     * Crea una unitat orgància amb codiDir3 "U87654321".
     */
  //  @Test
    @InSequence(1)
    public void testCreateUnitat() {
        UnitatOrganica unitatOrganica = new UnitatOrganica();
        unitatOrganica.setCodiDir3("U87654321");
        unitatOrganica.setNom("Unitat test arquillian");
        unitatOrganica.setDataCreacio(LocalDate.now());
        unitatOrganica.setEstat(EstatPublicacio.ACTIU);
        em.persist(unitatOrganica);
        em.flush();

        Assert.assertNotNull(unitatOrganica.getId());
    }

    /**
     * Selecciona la unitat orgànica amb codiDir3 "U87654321".
     */
   // @Test
    @InSequence(2)
    public void testQueryUnitat() {
        TypedQuery<UnitatOrganica> query = em.createQuery(
                "select u from UnitatOrganica u where u.codiDir3 = :codiDir3", UnitatOrganica.class);
        query.setParameter("codiDir3", "U87654321");
        UnitatOrganica unitat = query.getSingleResult();

        // Comprovam el nom de la unitat seleccionada
        Assert.assertEquals("Unitat test arquillian", unitat.getNom());
    }

    /**
     * Selecciona i esborra la unitat orgànica amb codiDir3 "U87654321".
     */
  //  @Test
    @InSequence(3)
    public void testRemoveUnitat() {
        TypedQuery<UnitatOrganica> query = em.createQuery(
                "select u from UnitatOrganica u where u.codiDir3 = :codiDir3", UnitatOrganica.class);
        query.setParameter("codiDir3", "U87654321");
        UnitatOrganica unitat = query.getSingleResult();
        em.remove(unitat);
        em.flush();

        // La unitat no hauria d'estar ja dins
        Assert.assertFalse(em.contains(unitat));
    }

    /**
     * Crea una unitat orgànica incomplint les validacons i intenta persistir-la per fer botar els errors de validació.
     */
  //  @Test
    @InSequence(4)
    public void testConstraintsUnitat() {

        UnitatOrganica unitatOrganica = new UnitatOrganica();
        unitatOrganica.setCodiDir3("Z87654321"); // inclumpleix el pattern
        unitatOrganica.setNom(""); // incumpleix que no potser buid
        unitatOrganica.setDataCreacio(LocalDate.now().plusDays(1)); // inclumpleix que no pot se futur
        unitatOrganica.setEstat(null); // inclumpleix que no pot ser null

        try {
            em.persist(unitatOrganica);
            em.flush();
            Assert.fail("Hauria d'haver donat un error de validació");
        } catch (ConstraintViolationException cve) {
            // Hi hauria d'haver 4 errors de validació.
            Assert.assertEquals(4, cve.getConstraintViolations().size());
        }
    }
}
