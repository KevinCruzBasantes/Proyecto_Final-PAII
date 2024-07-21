package models;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Producto producto;
    private ProgressObserver observer;
    private List<Observer> observers;

    public Pedido(Producto producto, ProgressObserver observer) {
        this.producto = producto;
        this.observer = observer;
        this.observers = new ArrayList<>();
    }

    public Producto getProducto() {
        return producto;
    }

    public ProgressObserver getObserver() {
        return observer;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
