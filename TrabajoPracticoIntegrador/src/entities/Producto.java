/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author valen
 */
public class Producto extends Base {
    
    private String nombre;
    private Double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private boolean disponible;
    private Long idCategoria; // Guardamos el ID de la categoría a la que pertenece

    /**
     * Constructor vacío.
     */
    public Producto() {
        super();
    }

    /**
     * Constructor con parámetros.
     */
    public Producto(String nombre, Double precio, String descripcion, int stock, String imagen, boolean disponible, Long idCategoria) {
        super();
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.imagen = imagen;
        this.disponible = disponible;
        this.idCategoria = idCategoria;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public Long getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Long idCategoria) { this.idCategoria = idCategoria; }

    @Override
    public String toString() {
        return super.toString() + 
               " | Producto: " + nombre + 
               " | Precio: $" + precio + 
               " | Stock: " + stock + 
               " | Disp: " + (disponible ? "Sí" : "No") + 
               " | ID Cat: " + idCategoria;
    }
}
