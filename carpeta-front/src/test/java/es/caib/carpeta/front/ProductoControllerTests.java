package es.caib.carpeta.front;


import es.caib.carpeta.core.CarpetaBusinessConfig;
import es.caib.carpeta.front.config.CarpetaFrontConfig;
import es.caib.carpeta.front.controllers.ProductoController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.ModelAndView;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CarpetaBusinessConfig.class, CarpetaFrontConfig.class})
@WebAppConfiguration
public class ProductoControllerTests {

    @Autowired
    private ProductoController productoController;

    @Test
    public void testProductos() {

        ModelAndView modelAndView = productoController.productos();
        assertEquals("productos", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        @SuppressWarnings("unchecked")
        String nowValue = (String)modelAndView.getModel().get("now");
        assertNotNull(nowValue);

    }

    @Test
    public void testIncrementar() {

        ModelAndView modelAndView = productoController.incrementarPrecio(10);
        assertEquals("productos", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        @SuppressWarnings("unchecked")
        String nowValue = (String)modelAndView.getModel().get("now");
        assertNotNull(nowValue);

    }
}
