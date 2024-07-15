package models;

public class Administrador {
    private String nombre;

    public Administrador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void aprobarPedido() {
        System.out.println("Pedido aprobado por " + nombre);
    }
}