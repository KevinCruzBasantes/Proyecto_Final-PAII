package models;

public class ProductoFactory {
    public static Producto crearProducto(String tipo) {
        switch (tipo) {
            case "madera":
                return new ProductoMadera();
            case "hierro":
                return new ProductoHierro();
            default:
                throw new IllegalArgumentException("Tipo de producto desconocido: " + tipo);
        }
    }
}