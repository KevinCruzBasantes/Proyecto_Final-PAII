package uce.edu.ec.ProyectoFinal;

import controller.Controlador;
import models.Administrador;
import models.Cliente;
import models.ProductoFactory;
import models.ProductoObserver;
import util.InicializadorDeProductos;
import util.ProductoAPI; // Importa el cliente API
import view.Vista;

import javax.swing.*;

public class ProyectoFinalApplication {

	public static void main(String[] args) {
		// Crea una instancia del cliente API
		ProductoAPI productoAPI = new ProductoAPI();

		// Inicializa los productos utilizando el cliente API
		InicializadorDeProductos inicializador = new InicializadorDeProductos(productoAPI);
		inicializador.inicializar(); // Esto guardará los productos en el proyecto de persistencia

		// Crea una instancia del factory con el API
		ProductoFactory productoFactory = new ProductoFactory(productoAPI);

		// Crea instancias de Cliente, Administrador y Controlador
		Cliente cliente = new Cliente("Juan"); // Asegúrate de que Cliente tenga un constructor que acepte un nombre
		Administrador administrador = new Administrador("Ana");
		Controlador controlador = new Controlador(administrador, productoFactory);
		ProductoObserver observer = new ProductoObserver();

		administrador.addObserver(observer);

		// Configura la vista y la invoca en el hilo de eventos de Swing
		SwingUtilities.invokeLater(() -> new Vista(controlador));
	}
}
