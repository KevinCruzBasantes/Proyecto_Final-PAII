package models;

public class Cliente {
    private String nombre;

    // Constructor sin parámetros
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
