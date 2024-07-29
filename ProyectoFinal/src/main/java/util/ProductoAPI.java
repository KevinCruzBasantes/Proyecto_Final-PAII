package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.List;

public class ProductoAPI {
    private static final String BASE_URL = "http://localhost:8080/productos";
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    // Constructor con parámetros
    public ProductoAPI(String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    // Constructor por defecto
    public ProductoAPI() {
        this(BASE_URL);  // Usar el BASE_URL por defecto
    }

    public ProductoDTO[] obtenerProductos() {
        String url = BASE_URL;
        try {
            String response = restTemplate.getForObject(url, String.class);
            return parseResponse(response).toArray(new ProductoDTO[0]);
        } catch (HttpClientErrorException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
            return new ProductoDTO[0];
        }
    }

    public ProductoDTO obtenerProductoPorNombre(String nombre) {
        String url = BASE_URL + "/buscar/ " + nombre;
        try {
            String response = restTemplate.getForObject(url, String.class);
            return parseSingleResponse(response);
        } catch (HttpClientErrorException e) {
            System.err.println("Error al obtener producto por nombre: " + e.getMessage());
            return null;
        }
    }

    public ProductoDTO crearOActualizarProducto(ProductoDTO producto) {
        String url = BASE_URL ;
        try {
            String response = restTemplate.postForObject(url, producto, String.class);
            return parseSingleResponse(response);
        } catch (HttpClientErrorException e) {
            System.err.println("Error al crear o actualizar producto: " + e.getMessage());
            return null;
        }
    }

    public void eliminarProducto(Long id) {
        String url = BASE_URL + "/" + id; ;
        try {
            restTemplate.delete(url);
        } catch (HttpClientErrorException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    private List<ProductoDTO> parseResponse(String response) {
        try {
            return objectMapper.readValue(response, new TypeReference<List<ProductoDTO>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private ProductoDTO parseSingleResponse(String response) {
        try {
            return objectMapper.readValue(response, ProductoDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
