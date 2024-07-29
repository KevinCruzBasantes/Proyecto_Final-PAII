package models;

public class ProductoHierro extends Producto {
    public ProductoHierro(String nombre, Long materialId, int stock) {
        super(nombre, materialId, stock);
    }

    @Override
    protected String obtenerMaterialPorId(Long id) {
        return "Hierro";
    }
}
