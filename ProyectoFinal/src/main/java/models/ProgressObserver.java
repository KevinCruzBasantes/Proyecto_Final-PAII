package models;

import util.ProductoDTO;

public interface ProgressObserver {
    void updateProgress(ProductoDTO producto, int progress);

    void updateStock(ProductoDTO producto, int stock);

    void updateProgress(String step, int progress, ProductoDTO producto);
}
