package uce.edu.ec.ProyectoFinal;

import controller.Controlador;
import models.Administrador;
import models.Cliente;
import models.ProductoFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import view.Vista;

//@SpringBootApplication
public class ProyectoFinalApplication {

	public static void main(String[] args) {
	//	SpringApplication.run(ProyectoFinalApplication.class, args);

		Cliente cliente = new Cliente("Juan");
		Administrador administrador = new Administrador("Ana");
		ProductoFactory productoFactory = new ProductoFactory();
		Controlador controlador = new Controlador(cliente, administrador, productoFactory);
		Vista vista = new Vista(controlador);

		vista.mostrarMenu();
	}


}
