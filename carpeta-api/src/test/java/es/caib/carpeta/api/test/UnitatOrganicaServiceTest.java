package es.caib.carpeta.api.test;


import es.caib.carpeta.commons.utils.BasicAuthenticator;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
/*
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.time.LocalDate;
*/
/**
 * Clase d'exemple de client de l'api REST. Empra l'api estàndard de Client de JAX-RS 2.1.
 * El test requereix que hi hagi una unitat orgànica amb id = 1 i codiDir3 = A00000001.
 * També requereix que no existeix el codiDir3 = U87654321
 */
public class UnitatOrganicaServiceTest {

    // URL a partir de la qual estan penjats els resources.
    private static final String BASE_URL = "http://localhost:8080/carpeta/api/services";

    // Nom d'usuari i password a emprar per les peticions que necesisten autenticació. Cal posar un
    // usuari/password que tengui rol CAR_ADMIN a per el mòdul web de l'api REST.
    private static final String USER = "app";
    private static final String PASSWORD = "app";

    // Client a reutilitzar durant test
    private static Client client;

    @BeforeClass
    public static void setUp() {
        // Construïm un client amb autenticació
        client = ClientBuilder.newClient().register(new BasicAuthenticator(USER, PASSWORD));
    }

    /**
     * Consulta la unitat amb codi 1.
     */
    @Test
    public void testGetUnitat() {
/*
        UnitatOrganica unitat = client.target(BASE_URL + "/unitats/1")
                .request(MediaType.APPLICATION_JSON)
                .get(UnitatOrganica.class);

        Assert.assertEquals(1L, (long) unitat.getId());
        Assert.assertEquals("A00000001", unitat.getCodiDir3());
        */
    }

    /**
     * Consulta totes les unitats.
     */
    @Test
    public void testGetAllUnitats() {
/*
        UnitatOrganica[] unitats = client.target(BASE_URL + "/unitats")
                .request(MediaType.APPLICATION_JSON)
                .get(UnitatOrganica[].class);

        Assert.assertTrue(unitats.length > 0);
        */
    }

    /**
     * Actualitza la unitat amb codi 1.
     */
    @Test
    public void testUpdateUnitat() {
/*
        UnitatOrganica unitat = new UnitatOrganica();
        unitat.setId(1L);
        unitat.setCodiDir3("A00000001");
        unitat.setNom("Updated by test");
        unitat.setDataCreacio(LocalDate.now());
        unitat.setEstat(EstatPublicacio.ACTIU);

        Response response = client.target(BASE_URL + "/unitats/1")
                .request()
                .put(Entity.json(unitat)); // Les actualitzacions són un mètode PUT

        // La resposta quan tot ha anat bé és un 204, ja que no envia contingut.
        Assert.assertEquals(204, response.getStatus());
        */
    }

    /**
     * Crea una nova unitat orgànica, la consulta comprovant que està com toca i l'esborra.
     */
    @Test
    public void testCreateAndDelete() {
/*
        // Dades de la nova unitat orgànica que crearem
        UnitatOrganica newUnitat = new UnitatOrganica();
        //no fixam id perquè és una creació
        newUnitat.setCodiDir3("U87654321"); // aquest codiDir3 no ha d'exisitir ja que és clau única
        newUnitat.setNom("Created by test");
        newUnitat.setDataCreacio(LocalDate.now());
        newUnitat.setEstat(EstatPublicacio.INACTIU);

        // Feim el POST per crear-la
        Response postResponse = client.target(BASE_URL + "/unitats")
                .request()
                .post(Entity.json(newUnitat));

        // La resposta quan tot ha anat bé és un 201, created
        Assert.assertEquals(201, postResponse.getStatus());

        // La resposta inclou la localitzacío del nou recurs creat.
        URI unitatURI = postResponse.getLocation();

        // Itentam consultar el recurs creat per comprovar que els camps arriben ingual que els hem enviat
        UnitatOrganica unitat = client.target(unitatURI)
                .request(MediaType.APPLICATION_JSON)
                .get(UnitatOrganica.class);
        Assert.assertEquals("U87654321", unitat.getCodiDir3());
        Assert.assertEquals("Created by test", unitat.getNom());
        Assert.assertEquals(LocalDate.now(), unitat.getDataCreacio()); // no començar el test a les 23:59:59 ;)
        Assert.assertEquals(EstatPublicacio.INACTIU, unitat.getEstat());

        // Finalment borram el test.
        Response deleteResponse = client.target(unitatURI)
                .request().delete();

        // La resposta quan tot ha anat bé és un 204, ja que no envia contingut.
        Assert.assertEquals(204, deleteResponse.getStatus());
        */
    }
}
