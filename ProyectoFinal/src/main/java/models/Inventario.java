package models;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<ProductoEnStock> stock;

    public Inventario() {
        stock = new ArrayList<>();
        // Inicializando con algunos productos y cantidades de ejemplo
        agregarProducto("madera", "silla", 10);
        agregarProducto("madera", "mesa", 10);
        agregarProducto("madera", "estanteria", 10);
        agregarProducto("madera", "armario", 10);
        agregarProducto("hierro", "silla", 10);
        agregarProducto("hierro", "mesa", 10);
        agregarProducto("hierro", "estanteria", 10);
        agregarProducto("hierro", "armario", 10);
    }

    public void agregarProducto(String tipo, String nombre, int cantidad) {
        ProductoEnStock producto = obtenerProductoEnStock(tipo, nombre);
        if (producto == null) {
            stock.add(new ProductoEnStock(tipo, nombre, cantidad));
        } else {
            producto.incrementarCantidad(cantidad);
        }
    }

    public int obtenerStock(String tipo, String nombre) {
        ProductoEnStock producto = obtenerProductoEnStock(tipo, nombre);
        return producto != null ? producto.getCantidad() : 0;
    }

    public boolean reducirCantidad(String tipo, String nombre, int cantidad) {
        ProductoEnStock producto = obtenerProductoEnStock(tipo, nombre);
        if (producto != null && producto.getCantidad() >= cantidad) {
            producto.decrementarCantidad(cantidad);
            return true;
        }
        return false;
    }

    private ProductoEnStock obtenerProductoEnStock(String tipo, String nombre) {
        for (ProductoEnStock producto : stock) {
            if (producto.getTipo().equals(tipo) && producto.getNombre().equals(nombre)) {
                return producto;
            }
        }
        return null;
    }
}

