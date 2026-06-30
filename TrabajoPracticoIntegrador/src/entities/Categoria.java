package entities;



import java.util.ArrayList;
import java.util.List;

public class Categoria extends Base {
    private String nombre;
    private String descripcion;
    private final List<Producto> productos = new ArrayList<>();

    public Categoria(String nombre, String descripcion) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    @Override
    public String toString() {
        return String.format("Categoria{id=%d, nombre='%s', descripcion='%s'}", id, nombre, descripcion);
    }
}
