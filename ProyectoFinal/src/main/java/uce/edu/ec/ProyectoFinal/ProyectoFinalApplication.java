package uce.edu.ec.ProyectoFinal;

import controller.Controlador;
import models.Administrador;
import models.Cliente;
import models.ProductoFactory;
import view.VistaCliente;
import view.VistaAdministrador;

import javax.swing.*;

public class ProyectoFinalApplication {

	public static void main(String[] args) {
		Cliente cliente = new Cliente("Juan");
		Administrador administrador = new Administrador("Ana");
		ProductoFactory productoFactory = new ProductoFactory();
		Controlador controlador = new Controlador(cliente, administrador, productoFactory);

		SwingUtilities.invokeLater(() -> {
			VistaAdministrador vistaAdministrador = new VistaAdministrador(controlador);
			controlador.setVistaAdministrador(vistaAdministrador);
			VistaCliente vistaCliente = new VistaCliente(controlador);
			controlador.setVistaCliente(vistaCliente);
		});
	}
}
