package models;


public class ProductoMadera extends Producto {
    public ProductoMadera() {
        super("Producto de Madera");
    }

    @Override
    public void manufacturar() {
        System.out.println("Manufacturando " + getNombre());
    }
}