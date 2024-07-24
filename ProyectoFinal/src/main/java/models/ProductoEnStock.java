package models;

public class ProductoEnStock {
    private String tipo;
    private String nombre;
    private int cantidad;

    public ProductoEnStock(String tipo, String nombre, int cantidad) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void incrementarCantidad(int cantidad) {
        this.cantidad += cantidad;
    }

    public void decrementarCantidad(int cantidad) {
        this.cantidad -= cantidad;
    }
}
