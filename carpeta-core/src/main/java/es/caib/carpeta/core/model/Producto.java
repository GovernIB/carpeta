package es.caib.carpeta.core.model;

import javax.persistence.*;

@Entity
@Table(name="car_producto")
@SequenceGenerator(name="generator",sequenceName = "producto_sq", allocationSize = 1)
public class Producto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    private Long id;
    private String descripcion;
    private Double precio;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Id: " + id + ";");
        buffer.append("Descripcion: " + descripcion + ";");
        buffer.append("Precio: " + precio);
        return buffer.toString();
    }
}
