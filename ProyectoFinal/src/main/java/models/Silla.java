package models;

public class Silla extends Producto {
    public Silla(String material, int cantidad) {
        super("Silla", material, cantidad);
    }

    @Override
    public void manufacturar(ProgressObserver observer) throws InterruptedException {
        observer.updateProgress("Construyendo sillas de " + getMaterial(), 25, this);
        Thread.sleep(3000);
        observer.updateProgress("Montando sillas de " + getMaterial(), 50, this);
        Thread.sleep(3000);
        observer.updateProgress("Pintando sillas de " + getMaterial(), 75, this);
        Thread.sleep(3000);
        observer.updateProgress("Sillas de " + getMaterial() + " listas", 100, this);
        Thread.sleep(3000);
    }
}
