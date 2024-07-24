package models;

public class ProductoHierro extends Producto {
    public ProductoHierro(String nombre) {
        super(nombre);
    }

    @Override
    public void manufacturar(ProgressObserver observer) throws InterruptedException {
        observer.updateProgress("Fundiendo hierro", 25, this);
        Thread.sleep(1000);
        observer.updateProgress("Moldeando", 50, this);
        Thread.sleep(1000);
        observer.updateProgress("Soldando", 75, this);
        Thread.sleep(1000);
        observer.updateProgress("Pintando", 100, this);
        Thread.sleep(1000);
    }

    @Override
    public String getMaterial() {
        return "hierro";
    }
}
