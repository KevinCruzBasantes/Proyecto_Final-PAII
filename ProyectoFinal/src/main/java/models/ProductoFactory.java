package models;

import java.util.HashMap;
import java.util.Map;

public class ProductoFactory {
    private Map<String, Producto> productosMadera;
    private Map<String, Producto> productosHierro;

    public ProductoFactory() {
        productosMadera = new HashMap<>();
        productosHierro = new HashMap<>();

        productosMadera.put("silla", new ProductoMadera("Silla de Madera", 5));
        productosMadera.put("mesa", new ProductoMadera("Mesa de Madera", 5));
        productosMadera.put("estanteria", new ProductoMadera("Estantería de Madera", 5));

        productosHierro.put("silla", new ProductoHierro("Silla de Hierro", 5));
        productosHierro.put("mesa", new ProductoHierro("Mesa de Hierro", 5));
        productosHierro.put("estanteria", new ProductoHierro("Estantería de Hierro", 5));
    }

    public Producto obtenerProducto(String tipo, String nombre) {
        if (tipo.equals("madera")) {
            return productosMadera.get(nombre);
        } else if (tipo.equals("hierro")) {
            return productosHierro.get(nombre);
        } else {
            throw new IllegalArgumentException("Tipo de producto desconocido: " + tipo);
        }
    }

    public int obtenerStock(String tipo, String nombre) {
        return obtenerProducto(tipo, nombre).getStock();
    }
}