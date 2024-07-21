package models;

public class Mesa extends Producto {
    public Mesa(String material, int cantidad) {
        super("Mesa", material, cantidad);
    }

    @Override
    public void manufacturar(ProgressObserver observer) throws InterruptedException {
        observer.updateProgress("Construyendo mesas de " + getMaterial(), 25, this);
        Thread.sleep(3000);
        observer.updateProgress("Montando mesas de " + getMaterial(), 50, this);
        Thread.sleep(3000);
        observer.updateProgress("Pintando mesas de " + getMaterial(), 75, this);
        Thread.sleep(3000);
        observer.updateProgress("Mesas de " + getMaterial() + " listas", 100, this);
        Thread.sleep(3000);
    }
}
