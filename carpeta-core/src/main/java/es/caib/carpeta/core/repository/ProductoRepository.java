package es.caib.carpeta.core.repository;

import es.caib.carpeta.core.model.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Long> {
}
