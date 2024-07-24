package models;

public abstract class Producto {
    private String nombre;
    private int cantidad;

    public Producto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public abstract void manufacturar(ProgressObserver observer) throws InterruptedException;

    public abstract String getMaterial();

    @Override
    public String toString() {
        return nombre + " (" + getMaterial() + ")";
    }
}
