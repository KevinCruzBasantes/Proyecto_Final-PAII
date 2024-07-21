package models;

import java.util.ArrayList;
import java.util.List;

public class Administrador {
    private String nombre;
    private List<Pedido> pedidos;

    public Administrador(String nombre) {
        this.nombre = nombre;
        this.pedidos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarPedido(Producto producto, ProgressObserver observer) {
        Pedido pedido = new Pedido(producto, observer);
        pedidos.add(pedido);
        notifyObservers(pedido);
    }

    public void aprobarPedido(Pedido pedido) {
        System.out.println("Pedido aprobado por " + nombre);
        try {
            pedido.getProducto().manufacturar(pedido.getObserver());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    private void notifyObservers(Pedido pedido) {
        for (Observer observer : pedido.getObservers()) {
            observer.update(pedido.getProducto());
        }
    }
}
