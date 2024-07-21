package models;


public class ProductoMadera extends Producto {
    public ProductoMadera(String nombre, int stock) {
        super(nombre, stock);
    }

    @Override
    public void manufacturar(ProgressObserver observer) throws InterruptedException {
        observer.updateProgress("Cortando madera", 25);
        Thread.sleep(5000);
        observer.updateProgress("Lijando madera", 50);
        Thread.sleep(5000);
        observer.updateProgress("Pintando madera", 75);
        Thread.sleep(5000);
        observer.updateProgress("Armando", 100);
        Thread.sleep(5000);
    }
}
