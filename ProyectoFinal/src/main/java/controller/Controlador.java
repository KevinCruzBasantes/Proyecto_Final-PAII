package controller;

import models.*;
import util.ClienteAPI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controlador {

    private Administrador administrador;
    private ProductoFactory productoFactory;
    private ExecutorService executorService;
    private List<Producto> carrito;
    private ClienteAPI clienteAPI;

    public Controlador(Administrador administrador, ProductoFactory productoFactory) {
        this.administrador = administrador;
        this.productoFactory = productoFactory;
        this.executorService = Executors.newFixedThreadPool(2);
        this.carrito = new ArrayList<>();
        this.clienteAPI = new ClienteAPI(); // Asegúrate de inicializar ClienteAPI
    }

    public List<Producto> getCarrito() {
        return carrito;
    }

    public void agregarAlCarrito(String tipo, String nombre) {
        Producto producto = productoFactory.obtenerProducto(tipo, nombre);
        if (producto == null) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado: tipo = " + tipo + ", nombre = " + nombre);
        } else if (producto.getStock() > 0) {
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

    public void agregarCliente(String nombre) {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre(nombre);
        clienteAPI.crearCliente(nuevoCliente);
    }
}
