package controller;

import models.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Controlador {
    private Cliente cliente;
    private Administrador administrador;
    private ProductoFactory productoFactory;
    private ExecutorService executorService;

    public Controlador(Cliente cliente, Administrador administrador, ProductoFactory productoFactory) {
        this.cliente = cliente;
        this.administrador = administrador;
        this.productoFactory = productoFactory;
        this.executorService = Executors.newFixedThreadPool(2);
    }

    public void realizarPedido(String tipoProducto, ProgressObserver observer) {
        Producto producto = productoFactory.crearProducto(tipoProducto);
        System.out.println(cliente.getNombre() + " ha solicitado un " + producto.getNombre());
        administrador.aprobarPedido(producto);
        executorService.submit(() -> {
            try {
                producto.manufacturar(observer);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
    }
}