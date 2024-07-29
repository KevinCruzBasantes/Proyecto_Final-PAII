package util;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import models.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;

public class ClienteAPI {
    private static final String BASE_URL = "http://localhost:8080/clientes";
    private final RestTemplate restTemplate;

    public ClienteAPI() {
        this.restTemplate = new RestTemplate();
    }

    // Método para obtener todos los clientes
    public Cliente[] obtenerClientes() {
        try {
            return restTemplate.getForObject(BASE_URL, Cliente[].class);
        } catch (RestClientException e) {
            System.out.println("Error al obtener clientes: " + e.getMessage());
            return new Cliente[0];
        }
    }

    // Método para obtener un cliente por ID
    public Cliente obtenerClientePorId(Long id) {
        try {
            return restTemplate.getForObject(BASE_URL + "/" + id, Cliente.class);
        } catch (RestClientException e) {
            System.out.println("Error al obtener cliente por ID: " + e.getMessage());
            return null;
        }
    }

    // Método para crear un nuevo cliente
    public Cliente crearCliente(Cliente cliente) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Cliente> entity = new HttpEntity<>(cliente, headers);

            ResponseEntity<Cliente> response = restTemplate.exchange(BASE_URL, HttpMethod.POST, entity, Cliente.class);

            if (response.getStatusCode() == HttpStatus.CREATED || response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                System.out.println("Error al crear cliente: " + response.getStatusCode());
                return null;
            }
        } catch (RestClientException e) {
            System.out.println("Error al crear cliente: " + e.getMessage());
            return null;
        }
    }


    // Método para eliminar un cliente por ID
    public void eliminarCliente(Long id) {
        try {
            restTemplate.delete(BASE_URL + "/" + id);
        } catch (RestClientException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
    }
}
