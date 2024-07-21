package models;

import util.ProductoAPI;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoFactory {
    private Map<String, Producto> productosMadera;
    private Map<String, Producto> productosHierro;
    private ProductoAPI productoAPI; // Asegúrate de inyectar ProductoAPI

    public ProductoFactory(ProductoAPI productoAPI) {
        this.productoAPI = productoAPI;
        productosMadera = new HashMap<>();
        productosHierro = new HashMap<>();
        cargarProductos(); // Cargar productos al inicializar
    }

    private void cargarProductos() {
        // Cargar productos de madera
        ProductoMadera[] maderaArray = productoAPI.obtenerProductosMadera();
        List<ProductoMadera> maderaList = Arrays.asList(maderaArray);
        for (ProductoMadera producto : maderaList) {
            productosMadera.put(producto.getNombre(), producto);
        }

        // Cargar productos de hierro
        ProductoHierro[] hierroArray = productoAPI.obtenerProductosHierro();
        List<ProductoHierro> hierroList = Arrays.asList(hierroArray);
        for (ProductoHierro producto : hierroList) {
            productosHierro.put(producto.getNombre(), producto);
        }
    }

    public Producto obtenerProducto(String tipo, String nombre) {
        Producto producto;
        if (tipo.equals("madera")) {
            producto = productosMadera.get(nombre);
        } else if (tipo.equals("hierro")) {
            producto = productosHierro.get(nombre);
        } else {
            throw new IllegalArgumentException("Tipo de producto desconocido: " + tipo);
        }
        if (producto == null) {
            System.out.println("Producto no encontrado: tipo = " + tipo + ", nombre = " + nombre);
        }
        return producto;
    }

    public int obtenerStock(String tipo, String nombre) {
        Producto producto = obtenerProducto(tipo, nombre);
        if (producto != null) {
            return producto.getStock();
        } else {
            // Maneja el caso cuando el producto no existe
            System.out.println("Producto no encontrado: tipo = " + tipo + ", nombre = " + nombre);
            return 0; // O alguna otra lógica para manejar productos no encontrados
        }
    }
}
