package es.caib.carpeta.back.controllers.rest;

import es.caib.carpeta.core.model.Producto;
import es.caib.carpeta.core.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto/")
public class ProductoRestController {

    private static final Logger log = LoggerFactory.getLogger(ProductoRestController.class);
    
    @Autowired
    private ProductoService productoService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Producto createProducto(@RequestBody Producto producto) throws Exception{
        return productoService.crearProducto(producto);
    }

    @GetMapping(value = "{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Producto getProducto(@PathVariable("id") Long id) throws Exception {
        return productoService.obtenerProducto(id);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Producto updateProducto(@RequestBody Producto producto) throws Exception {
        return productoService.actualizarProducto(producto);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProducto(@PathVariable("id") Long id) throws Exception{
        productoService.eliminarProducto(id);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void personExceptionHandler(final Exception exception) {
        log.info("REST Service exception: " + exception.getMessage());
    }
}
