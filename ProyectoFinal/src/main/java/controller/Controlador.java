package controller;

import models.Administrador;
import models.Cliente;
import models.Producto;
import models.ProductoFactory;

public class Controlador {
    private Cliente cliente;
    private Administrador administrador;
    private ProductoFactory productoFactory;

    public Controlador(Cliente cliente, Administrador administrador, ProductoFactory productoFactory) {
        this.cliente = cliente;
        this.administrador = administrador;
        this.productoFactory = productoFactory;
    }

    public void realizarPedido(String tipoProducto) {
        Producto producto = productoFactory.crearProducto(tipoProducto);
        System.out.println(cliente.getNombre() + " ha solicitado un " + producto.getNombre());
        administrador.aprobarPedido();
        producto.manufacturar();
    }
}