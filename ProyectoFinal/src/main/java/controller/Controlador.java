package controller;

import models.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controlador {
    private Cliente cliente;
    private Administrador administrador;
    private ProductoFactory productoFactory;
    private ExecutorService executorService;
    private view.VistaAdministrador vistaAdministrador;
    private view.VistaCliente vistaCliente;

    public Controlador(Cliente cliente, Administrador administrador, ProductoFactory productoFactory) {
        this.cliente = cliente;
        this.administrador = administrador;
        this.productoFactory = productoFactory;
        this.executorService = Executors.newFixedThreadPool(2);
    }

    public void setVistaAdministrador(view.VistaAdministrador vistaAdministrador) {
        this.vistaAdministrador = vistaAdministrador;
    }

    public void setVistaCliente(view.VistaCliente vistaCliente) {
        this.vistaCliente = vistaCliente;
    }

    public void realizarPedido(String tipoProducto, String material, int cantidad) {
        Producto producto = productoFactory.crearProducto(tipoProducto, material, cantidad);
        vistaCliente.agregarPedido(producto);
        vistaAdministrador.agregarPedido(producto);
    }

    public void aprobarPedido(Producto producto, boolean fabricar) {
        if (fabricar) {
            executorService.submit(() -> {
                try {
                    producto.manufacturar((step, progress, prod) -> vistaAdministrador.updateProgress(step, progress, prod));
                    vistaCliente.notificarPedido(producto, true);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    vistaCliente.notificarPedido(producto, false);
                }
            });
        } else {
            vistaAdministrador.resetProgress(producto);
            vistaCliente.notificarPedido(producto, false);
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
