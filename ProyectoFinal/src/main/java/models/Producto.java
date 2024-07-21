package models;

public abstract class Producto {
    private String nombre;
    private String material;
    private int cantidad;

    public Producto(String nombre, String material, int cantidad) {
        this.nombre = nombre;
        this.material = material;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMaterial() {
        return material;
    }

    public int getCantidad() {
        return cantidad;
    }
    @Override
    public String toString() {
        return nombre + " (" + material + ", " + cantidad + " unidades)";
    }
    public abstract void manufacturar(ProgressObserver observer) throws InterruptedException;
}
