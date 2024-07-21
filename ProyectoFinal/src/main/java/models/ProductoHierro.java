package models;
public class ProductoHierro extends Producto {
    public ProductoHierro(String nombre, int stock) {
        super(nombre, stock);
    }

    @Override
    public void manufacturar(ProgressObserver observer) throws InterruptedException {
        observer.updateProgress("Cortando hierro", 25);
        Thread.sleep(5000);
        observer.updateProgress("Soldando hierro", 50);
        Thread.sleep(5000);
        observer.updateProgress("Pintando hierro", 75);
        Thread.sleep(5000);
        observer.updateProgress("Montando", 100);
        Thread.sleep(5000);
    }
}