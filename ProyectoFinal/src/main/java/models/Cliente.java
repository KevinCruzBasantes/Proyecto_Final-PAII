package models;

public class Cliente {
    private Long id;  // ID del cliente
    private String nombre;  // Nombre del cliente

    // Constructor vacío (necesario para la deserialización JSON)
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
