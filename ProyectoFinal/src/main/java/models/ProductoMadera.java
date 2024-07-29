package models;

public class ProductoMadera extends Producto {
    public ProductoMadera(String nombre, Long materialId, int stock) {
        super(nombre, materialId, stock);
    }

    @Override
    protected String obtenerMaterialPorId(Long id) {
        return "Madera";
    }
}
