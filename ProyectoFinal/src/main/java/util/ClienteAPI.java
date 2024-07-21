package util;

import org.springframework.web.client.RestTemplate;
import models.Cliente;

public class ClienteAPI {
    private static final String BASE_URL = "http://localhost:8080/clientes";

    public Cliente[] obtenerClientes() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(BASE_URL, Cliente[].class);
    }

    public Cliente obtenerClientePorId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(BASE_URL + "/" + id, Cliente.class);
    }

    public Cliente crearCliente(Cliente cliente) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(BASE_URL, cliente, Cliente.class);
    }

    public void eliminarCliente(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(BASE_URL + "/" + id);
    }
}
