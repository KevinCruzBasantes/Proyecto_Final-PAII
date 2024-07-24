package models;

public class ProductoFactory {
    public Producto obtenerProducto(String tipoProducto, String nombreProducto) {
        switch (tipoProducto) {
            case "madera":
                return new ProductoMadera(nombreProducto);
            case "hierro":
                return new ProductoHierro(nombreProducto);
            default:
                throw new IllegalArgumentException("Tipo de producto no soportado: " + tipoProducto);
        }
    }
}
