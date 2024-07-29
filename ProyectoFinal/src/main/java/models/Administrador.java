package models;

import util.ProductoDTO;
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

    public void agregarPedido(ProductoDTO producto, int cantidad, int stockDisponible) {
        Pedido pedido = new Pedido(producto, cantidad, stockDisponible);
        pedidos.add(pedido);
        notifyObservers(pedido);
    }

    public void aprobarPedido(Pedido pedido) {
        System.out.println("Pedido aprobado por " + nombre);
        try {
            ProgressObserver observer = pedido.getObservers().get(0);
            pedido.getProducto().manufacturar(observer);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    private void notifyObservers(Pedido pedido) {
        for (ProgressObserver observer : pedido.getObservers()) {
            observer.updateProgress("Notificación inicial", 0, pedido.getProducto());
        }
    }
}
