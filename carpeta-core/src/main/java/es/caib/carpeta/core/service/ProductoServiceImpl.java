package es.caib.carpeta.core.service;

import es.caib.carpeta.core.model.Producto;
import es.caib.carpeta.core.repository.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{

    private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);

    @Autowired
    private ProductoRepository productoRepository;


    public Producto crearProducto(Producto producto) throws Exception {

        try{
            return productoRepository.save(producto);
        }catch (Exception e){
            log.info("Error creando registro : " + e.getMessage());
            throw new Exception(e.getLocalizedMessage());
        }
    }

    public Producto actualizarProducto(Producto producto) throws Exception {

        try{
            return productoRepository.save(producto);
        }catch (Exception e){
            log.info("Error actualizando registro : " + e.getMessage());
            throw new Exception(e.getLocalizedMessage());
        }
    }

    public Producto obtenerProducto(Long id) throws Exception {

        return productoRepository.findOne(id);

    }

    public void eliminarProducto(Long id) throws Exception {
        productoRepository.delete(id);
    }

    public void incrementarPrecio(int porcetaje) {

        List<Producto> productos = getProductos();

        if (productos != null) {
            for (Producto producto : productos) {
                double newPrice = producto.getPrecio().doubleValue() *
                        (100 + porcetaje)/100;
                producto.setPrecio(newPrice);
                productoRepository.save(producto);
            }
        }
    }

    public List<Producto> getProductos() {
        return (List<Producto>)  productoRepository.findAll();
    }

    public String test() {
        return "...Dentro de service....";
    }

}
