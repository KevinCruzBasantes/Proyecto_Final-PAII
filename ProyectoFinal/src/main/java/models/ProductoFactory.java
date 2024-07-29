package models;

public class ProductoFactory {
    public static Producto obtenerProducto(String tipo, String nombre, int stock) {
        Long materialId = obtenerMaterialId(tipo);

        switch (tipo.toLowerCase()) {
            case "madera":
                return new ProductoMadera(nombre, materialId, stock);
            case "hierro":
                return new ProductoHierro(nombre, materialId, stock);
            default:
                throw new IllegalArgumentException("Tipo de producto no soportado: " + tipo);
        }
    }

    private static Long obtenerMaterialId(String tipo) {
        switch (tipo.toLowerCase()) {
            case "madera":
                return 1L; // ID para madera
            case "hierro":
                return 2L; // ID para hierro
            default:
                return null;
        }
    }
}
