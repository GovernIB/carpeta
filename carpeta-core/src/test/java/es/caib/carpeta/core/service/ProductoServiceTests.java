package es.caib.carpeta.core.service;

import es.caib.carpeta.core.CarpetaBusinessConfig;
import es.caib.carpeta.core.model.Producto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CarpetaBusinessConfig.class})
public class ProductoServiceTests {

    @Autowired
    private ProductoService productoService;

    private List<Producto> productos;

    private static int PRODUCT_COUNT = 2;

    private static Double CHAIR_PRICE = new Double(20.50);
    private static String CHAIR_DESCRIPTION = "Chair";

    private static String TABLE_DESCRIPTION = "Table";
    private static Double TABLE_PRICE = new Double(150.10);

    private static int POSITIVE_PRICE_INCREASE = 10;

    @Before
    public void setUp() throws Exception {
        productoService = new ProductoServiceImpl();

        productos = productoService.getProductos();

    }

    @Test
    public void testGetProductsWithNoProducts() {
        List<Producto> products = productoService.getProductos();
        assertNotNull(products);
        assertEquals(PRODUCT_COUNT, productoService.getProductos().size());

        Producto producto = products.get(0);
        assertEquals(CHAIR_DESCRIPTION, ((Producto) producto).getDescripcion());
        assertEquals(CHAIR_PRICE, producto.getPrecio());

        producto = products.get(1);
        assertEquals(TABLE_DESCRIPTION, producto.getDescripcion());
        assertEquals(TABLE_PRICE, ((Producto) producto).getPrecio());
    }

    @Test
    public void testIncreasePriceWithNullListOfProducts() {
        try {
            productoService = new ProductoServiceImpl();
            productoService.incrementarPrecio(POSITIVE_PRICE_INCREASE);
        }
        catch(NullPointerException ex) {
            fail("Products list is null.");
        }
    }

    @Test
    public void testIncreasePriceWithEmptyListOfProducts() {
        try {
            productoService.incrementarPrecio(POSITIVE_PRICE_INCREASE);
        }
        catch(Exception ex) {
            fail("Products list is empty.");
        }
    }

    @Test
    public void testIncreasePriceWithPositivePercentage() {
        productoService.incrementarPrecio(POSITIVE_PRICE_INCREASE);
        double expectedChairPriceWithIncrease = 22.55;
        double expectedTablePriceWithIncrease = 165.11;

        List<Producto> products = productoService.getProductos();
        Producto product = products.get(0);
        assertEquals(expectedChairPriceWithIncrease, product.getPrecio(), 0);

        product = products.get(1);
        assertEquals(expectedTablePriceWithIncrease, product.getPrecio(), 0);
    }
}
