package models;

public class ProductoHierro extends Producto {
    public ProductoHierro() {
        super("Producto de Hierro");
    }

    @Override
    public void manufacturar() {
        System.out.println("Manufacturando " + getNombre());
    }
}
