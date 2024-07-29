package models;

import util.ProductoDTO;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private ProductoDTO producto;
    private List<ProgressObserver> observers;
    private int cantidad;
    private int stockDisponible;

    public Pedido(ProductoDTO producto, int cantidad, int stockDisponible) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.stockDisponible = stockDisponible;
        this.observers = new ArrayList<>();

    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public List<ProgressObserver> getObservers() {
        return observers;
    }

    public void addObserver(ProgressObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message, int progreso) {
        for (ProgressObserver observer : observers) {
            observer.updateProgress(message, progreso, producto);
        }
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }
}
