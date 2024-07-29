package controller;

import models.Administrador;
import models.Cliente;
import models.ProductoFactory;
import util.ClienteAPI;
import util.ProductoAPI;
import util.ProductoDTO;
import view.VistaAdministrador;
import view.VistaCliente;

import java.util.HashMap;
import java.util.Map;

public class Controlador {
    private Cliente cliente;
    private Administrador administrador;
    private ProductoFactory productoFactory;
    private ClienteAPI clienteAPI;
    private ProductoAPI productoAPI;
    private VistaAdministrador vistaAdministrador;
    private VistaCliente vistaCliente;
    private Map<String, Integer> carritoDeCompras;

    public Controlador(Cliente cliente, Administrador administrador, ProductoFactory productoFactory, ClienteAPI clienteAPI, ProductoAPI productoAPI) {
        this.cliente = cliente;
        this.administrador = administrador;
        this.productoFactory = productoFactory;
        this.clienteAPI = clienteAPI;
        this.productoAPI = productoAPI;
        this.carritoDeCompras = new HashMap<>();
    }

    public void setVistaAdministrador(VistaAdministrador vistaAdministrador) {
        this.vistaAdministrador = vistaAdministrador;
    }

    public void setVistaCliente(VistaCliente vistaCliente) {
        this.vistaCliente = vistaCliente;
    }

    public void agregarProducto(String material, String nombre, int cantidad) {
        // Añade el producto al carrito de compras
        carritoDeCompras.put(nombre, carritoDeCompras.getOrDefault(nombre, 0) + cantidad);
    }

    public Map<String, Integer> obtenerProductosCarrito() {
        return carritoDeCompras;
    }

    public void realizarPedido() {

        if (vistaAdministrador != null) {
            vistaAdministrador.cargarProductosDesdeCarrito();
        }
    }



    public ProductoDTO agregarProducto(ProductoDTO producto) {
        // Llama a ProductoAPI para crear o actualizar el producto
        ProductoDTO productoCreado = productoAPI.crearOActualizarProducto(producto);

        // Actualiza la vista del administrador
        if (vistaAdministrador != null) {
            vistaAdministrador.cargarProductosDesdeCarrito();
        }

        // Actualiza la vista del cliente
        if (vistaCliente != null) {
            vistaCliente.cargarProductos();
        }

        return productoCreado;
    }

    public void eliminarProducto(Long id) {
        // Llama a ProductoAPI para eliminar el producto
        productoAPI.eliminarProducto(id);

        // Actualiza la vista del administrador
        if (vistaAdministrador != null) {
            vistaAdministrador.cargarProductosDesdeCarrito();
        }

        // Actualiza la vista del cliente
        if (vistaCliente != null) {
            vistaCliente.cargarProductos();
        }
    }
}
