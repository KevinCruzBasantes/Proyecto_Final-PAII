package controller;

import models.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Controlador {
    private Cliente cliente;
    private Administrador administrador;
    private ProductoFactory productoFactory;
    private ExecutorService executorService;
    private List<Producto> carrito;

    public Controlador(Cliente cliente, Administrador administrador, ProductoFactory productoFactory) {
        this.cliente = cliente;
        this.administrador = administrador;
        this.productoFactory = productoFactory;
        this.executorService = Executors.newFixedThreadPool(2);
        this.carrito = new ArrayList<>();
    }

    public List<Producto> getCarrito() {
        return carrito;
    }

    public void agregarAlCarrito(String tipo, String nombre) {
        Producto producto = productoFactory.obtenerProducto(tipo, nombre);
        if (producto.getStock() > 0) {
            carrito.add(producto);
            producto.reducirStock();
        } else {
            JOptionPane.showMessageDialog(null, "El producto " + nombre + " de tipo " + tipo + " está fuera de stock.");
        }
    }

    public void realizarPedido(ProgressObserver observer) {
        for (Producto producto : carrito) {
            administrador.aprobarPedido(producto);
            executorService.submit(() -> {
                try {
                    producto.manufacturar(observer);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        carrito.clear();
    }

    public int obtenerStock(String tipo, String nombre) {
        return productoFactory.obtenerStock(tipo, nombre);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}