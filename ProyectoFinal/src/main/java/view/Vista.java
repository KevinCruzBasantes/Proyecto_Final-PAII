package view;
import controller.Controlador;

import java.util.Scanner;

public class Vista {
    private Controlador controlador;

    public Vista(Controlador controlador) {
        this.controlador = controlador;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Solicitar Producto de Madera");
        System.out.println("2. Solicitar Producto de Hierro");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                controlador.realizarPedido("madera");
                break;
            case 2:
                controlador.realizarPedido("hierro");
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
        scanner.close();
    }
}