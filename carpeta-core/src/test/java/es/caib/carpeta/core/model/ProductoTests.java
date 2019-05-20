package es.caib.carpeta.core.model;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ProductoTests {

    private Producto producto;

    @Before
    public void setUp() throws Exception {
        producto = new Producto();
    }

    @Test
    public void testSetAndGetDescription() {
        String testDescription = "Description";
        TestCase.assertNull(producto.getDescripcion());
        producto.setDescripcion(testDescription);
        Assert.assertEquals(testDescription, producto.getDescripcion());
    }

    @Test
    public void testSetAndGetPrice() {
        double testPrice = 100.00;
        Assert.assertEquals(0, 0, 0);
        producto.setPrecio(testPrice);
        Assert.assertEquals(testPrice, producto.getPrecio(), 0);
    }
}
