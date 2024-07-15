package models;

public class ProductoObserver implements Observer {
    @Override
    public void update(Producto producto) {
        System.out.println("Notificación: Se está creando " + producto.getNombre());
    }
}