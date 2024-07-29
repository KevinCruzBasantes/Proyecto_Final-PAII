package uce.edu.ec.ProyectoFinal;

import controller.Controlador;
import models.*;
import util.ClienteAPI;
import util.ProductoAPI;
import view.*;

import javax.swing.*;

public class ProyectoFinalApplication {
	public static void main(String[] args) {
		// Crear instancias de los modelos y API
		Cliente cliente = new Cliente("ClienteEjemplo");
		Administrador administrador = new Administrador("AdministradorEjemplo");
		ProductoFactory productoFactory = new ProductoFactory();
		ProductoAPI productoAPI = new ProductoAPI();
		ClienteAPI clienteAPI = new ClienteAPI();

		// Crear instancia del controlador con las dependencias
		Controlador controlador = new Controlador(cliente, administrador, productoFactory, clienteAPI, productoAPI);

		// Configurar la interfaz gráfica
		SwingUtilities.invokeLater(() -> {
			// Crear y mostrar la vista AgregarClienteView
			AgregarClienteView agregarClienteView = new AgregarClienteView(clienteAPI);
			agregarClienteView.setVisible(true);

			// Crear las vistas adicionales
			VistaCliente vistaCliente = new VistaCliente(controlador);
			VistaAdministrador vistaAdministrador = new VistaAdministrador(controlador);

			// Configurar el controlador con las vistas
			controlador.setVistaCliente(vistaCliente);
			controlador.setVistaAdministrador(vistaAdministrador);

			// Configurar VistaAdministrador con referencia a VistaCliente
			vistaAdministrador.setVistaCliente(vistaCliente);

			// Configurar AgregarClienteView para mostrar las otras vistas después de agregar cliente
			agregarClienteView.setOnClienteAdded(() -> {
				vistaAdministrador.setVisible(true);
				vistaCliente.setVisible(true);
			});
		});
	}
}
