package models;

public class ProductoHierro extends Producto {
    public ProductoHierro(String nombre, int cantidad) {
        super(nombre, "hierro", cantidad);
    }

    @Override
    public void manufacturar(ProgressObserver observer) throws InterruptedException {
        observer.updateProgress("Cortando hierro", 25, this);
        Thread.sleep(3000);
        observer.updateProgress("Soldando hierro", 50, this);
        Thread.sleep(3000);
        observer.updateProgress("Pintando hierro", 75, this);
        Thread.sleep(3000);
        observer.updateProgress("Montando", 100, this);
        Thread.sleep(3000);
    }
}
