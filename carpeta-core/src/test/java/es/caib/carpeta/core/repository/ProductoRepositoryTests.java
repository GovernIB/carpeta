package es.caib.carpeta.core.repository;

import es.caib.carpeta.core.CarpetaBusinessConfig;
import es.caib.carpeta.core.model.Producto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CarpetaBusinessConfig.class})
public class ProductoRepositoryTests {

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    ProductoRepository productoRepository;

    @Test
    public void testGetProductList() {
        List<Producto> productos = (List<Producto>) productoRepository.findAll();
        assertEquals(productos.size(), 3, 0);
    }

    @Test
    public void testSaveProduct() {
        List<Producto> products = (List<Producto>) productoRepository.findAll();

        Producto p = products.get(0);
        System.out.println("Producto: " + p.toString());
        Double price = p.getPrecio();
        p.setPrecio(200.12);
        productoRepository.save(p);

        List<Producto> updatedProducts = (List<Producto>) productoRepository.findAll();
        Producto p2 = updatedProducts.get(0);
        System.out.println("Producto: " + p2.toString());
        assertEquals(p2.getPrecio(), 200.12, 0);

        p2.setPrecio(price);
        productoRepository.save(p2);
    }
}
