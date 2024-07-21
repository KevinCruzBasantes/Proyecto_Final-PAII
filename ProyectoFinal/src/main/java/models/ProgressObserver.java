package models;

public interface ProgressObserver {
    void updateProgress(String step, int progress, Producto producto);
}
