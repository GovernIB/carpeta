package es.caib.carpeta.core.service;

import es.caib.carpeta.core.model.Producto;

import java.util.List;

public interface ProductoService {

    Producto crearProducto(Producto producto) throws Exception;

    Producto actualizarProducto(Producto producto) throws Exception;

    Producto obtenerProducto(Long id) throws Exception;

    void eliminarProducto(Long id) throws Exception;

    void incrementarPrecio(int porcetaje);

    List<Producto> getProductos();

    String test();
}
