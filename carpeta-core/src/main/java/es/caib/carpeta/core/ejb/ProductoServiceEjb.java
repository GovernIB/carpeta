package es.caib.carpeta.core.ejb;

import es.caib.carpeta.core.model.Producto;
import es.caib.carpeta.core.service.ProductoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateless
@Interceptors(SpringBeanAutowiringInterceptor.class)
@RolesAllowed({"CAR_ADMIN", "CAR_USUARI", "CAR_SUPORT"})
public class ProductoServiceEjb implements ProductoService {

    @Autowired
    ProductoService productoService;


    @Override
    public Producto crearProducto(Producto producto) throws Exception {
        return productoService.crearProducto(producto);
    }

    @Override
    public Producto actualizarProducto(Producto producto) throws Exception {
        return productoService.actualizarProducto(producto);
    }

    @Override
    public Producto obtenerProducto(Long id) throws Exception {
        return productoService.obtenerProducto(id);
    }

    @Override
    public void eliminarProducto(Long id) throws Exception {
        productoService.eliminarProducto(id);
    }

    @Override
    public void incrementarPrecio(int porcetaje) {
        productoService.incrementarPrecio(porcetaje);
    }

    @Override
    public List<Producto> getProductos() {
        return productoService.getProductos();
    }

    @Override
    public String test() {
        return "...Dentro de ejb....";
    }

}
