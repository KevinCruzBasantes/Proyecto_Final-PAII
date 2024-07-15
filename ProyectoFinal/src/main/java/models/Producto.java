package models;

public abstract class Producto {
    private String nombre;

    public Producto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void manufacturar(ProgressObserver observer) throws InterruptedException;
}