package models;

public class ProductoMadera extends Producto {
    public ProductoMadera(String nombre, int cantidad) {
        super(nombre, "madera", cantidad);
    }

    @Override
    public void manufacturar(ProgressObserver observer) throws InterruptedException {
        observer.updateProgress("Cortando madera", 25, this);
        Thread.sleep(3000);
        observer.updateProgress("Lijando madera", 50, this);
        Thread.sleep(3000);
        observer.updateProgress("Pintando madera", 75, this);
        Thread.sleep(3000);
        observer.updateProgress("Armando", 100, this);
        Thread.sleep(3000);
    }
}
