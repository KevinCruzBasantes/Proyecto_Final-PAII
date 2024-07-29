package models;

import util.ProductoDTO;

public class ProductoObserver implements ProgressObserver {

    @Override
    public void updateProgress(ProductoDTO producto, int progress) {

    }

    @Override
    public void updateStock(ProductoDTO producto, int stock) {

    }

    @Override
    public void updateProgress(String step, int progress, ProductoDTO producto) {
        // Implementa la lógica para manejar la actualización del progreso
        System.out.println("Paso: " + step + ", Progreso: " + progress + "% para el producto " + producto.getNombre());
    }
}
