package models;

import java.util.ArrayList;
import java.util.List;

public class Administrador {
    private String nombre;
    private List<Observer> observers;

    public Administrador(String nombre) {
        this.nombre = nombre;
        this.observers = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Producto producto) {
        for (Observer observer : observers) {
            observer.update(producto);
        }
    }

    public void aprobarPedido(Producto producto) {
        System.out.println("Pedido aprobado por " + nombre);
        notifyObservers(producto);
    }
}
