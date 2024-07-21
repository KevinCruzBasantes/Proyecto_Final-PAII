package models;

public class ProductoFactory {
    public Producto crearProducto(String tipoProducto, String material, int cantidad) {
        switch (tipoProducto) {
            case "silla":
                return material.equals("madera") ? new ProductoMadera("Silla", cantidad) : new ProductoHierro("Silla", cantidad);
            case "mesa":
                return material.equals("madera") ? new ProductoMadera("Mesa", cantidad) : new ProductoHierro("Mesa", cantidad);
            default:
                throw new IllegalArgumentException("Tipo de producto desconocido: " + tipoProducto);
        }
    }
}
