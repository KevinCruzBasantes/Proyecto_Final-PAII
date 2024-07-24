package controller;

import models.*;
import view.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controlador {
    private Cliente cliente;
    private Administrador administrador;
    private ProductoFactory productoFactory;
    private ExecutorService executorService;
    private VistaAdministrador vistaAdministrador;
    private VistaCliente vistaCliente;
    private Inventario inventario;

    public Controlador(Cliente cliente, Administrador administrador, ProductoFactory productoFactory, Inventario inventario) {
        this.cliente = cliente;
        this.administrador = administrador;
        this.productoFactory = productoFactory;
        this.executorService = Executors.newFixedThreadPool(2);
        this.inventario = inventario;
    }

    public void setVistaAdministrador(VistaAdministrador vistaAdministrador) {
        this.vistaAdministrador = vistaAdministrador;
    }

    public void setVistaCliente(VistaCliente vistaCliente) {
        this.vistaCliente = vistaCliente;
    }

    public void realizarPedido(String tipoProducto, String nombreProducto, int cantidad) {
        int stockDisponible = inventario.obtenerStock(tipoProducto, nombreProducto);
        if (stockDisponible >= cantidad) {
            try {
                Producto producto = productoFactory.obtenerProducto(tipoProducto, nombreProducto);
                if (producto != null) {
                    producto.setCantidad(cantidad);
                    vistaCliente.agregarPedido(producto);
                    vistaAdministrador.agregarPedido(producto, cantidad, stockDisponible);
                    vistaCliente.actualizarEstadoPedidos(producto.getNombre() + " (" + producto.getMaterial() + "): En espera");
                } else {
                    System.out.println("Producto no encontrado: " + nombreProducto);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            vistaCliente.notificarPedido(null, false);
        }
    }

    public void aprobarPedido(Producto producto, boolean fabricar) {
        if (fabricar) {
            int cantidad = producto.getCantidad();
            String tipo = producto.getMaterial();
            String nombre = producto.getNombre();

            if (inventario.reducirCantidad(tipo, nombre, cantidad)) {
                executorService.submit(() -> {
                    for (int i = 0; i < cantidad; i++) {
                        try {
                            producto.manufacturar(vistaAdministrador);
                            vistaCliente.notificarPedido(producto, true);
                            vistaCliente.actualizarEstadoPedidos("El producto " + producto.getNombre() + " ha sido creado con éxito.");
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            vistaCliente.notificarPedido(producto, false);
                            vistaCliente.actualizarEstadoPedidos(producto.getNombre() + " (" + producto.getMaterial() + "): Rechazado");
                        }
                    }
                    vistaAdministrador.actualizarStock(producto, inventario.obtenerStock(tipo, nombre));
                });
            } else {
                vistaCliente.notificarPedido(producto, false);
                vistaCliente.actualizarEstadoPedidos(producto.getNombre() + " (" + producto.getMaterial() + "): Rechazado");
            }
        } else {
            vistaCliente.notificarPedido(producto, false);
            vistaCliente.actualizarEstadoPedidos(producto.getNombre() + " (" + producto.getMaterial() + "): Rechazado");
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }
}