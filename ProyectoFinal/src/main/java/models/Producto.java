package models;

public abstract class Producto {
    private String nombre;
    private int stock;

    public Producto(String nombre, int stock) {
        this.nombre = nombre;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }

    public void reducirStock() {
        if (stock > 0) {
            stock--;
        }
    }

    public void aumentarStock() {
        stock++;
    }

    public abstract void manufacturar(ProgressObserver observer) throws InterruptedException;
}