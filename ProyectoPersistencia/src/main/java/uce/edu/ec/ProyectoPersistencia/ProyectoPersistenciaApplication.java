package uce.edu.ec.ProyectoPersistencia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"uce.edu.ec.ProyectoPersistencia", "controller", "models", "services", "repository"})
@EnableJpaRepositories(basePackages = "repository")
@EntityScan(basePackages = "models")  // Asegúrate de agregar esta línea
public class ProyectoPersistenciaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoPersistenciaApplication.class, args);
	}

}
