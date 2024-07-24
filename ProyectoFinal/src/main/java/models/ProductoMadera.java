package models;

public class ProductoMadera extends Producto {
    public ProductoMadera(String nombre) {
        super(nombre);
    }

    @Override
    public void manufacturar(ProgressObserver observer) throws InterruptedException {
        observer.updateProgress("Cortando madera", 25, this);
        Thread.sleep(1000);
        observer.updateProgress("Ensamblando", 50, this);
        Thread.sleep(1000);
        observer.updateProgress("Lijando", 75, this);
        Thread.sleep(1000);
        observer.updateProgress("Barnizando", 100, this);
        Thread.sleep(1000);
    }

    @Override
    public String getMaterial() {
        return "madera";
    }
}
