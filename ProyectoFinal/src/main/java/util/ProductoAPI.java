package util;

import models.ProductoMadera;
import models.ProductoHierro;
import org.springframework.web.client.RestTemplate;

public class ProductoAPI {
    private static final String BASE_URL_MADERA = "http://localhost:8080/productos/madera";
    private static final String BASE_URL_HIERRO = "http://localhost:8080/productos/hierro";

    public ProductoMadera[] obtenerProductosMadera() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(BASE_URL_MADERA, ProductoMadera[].class);
    }

    public ProductoHierro[] obtenerProductosHierro() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(BASE_URL_HIERRO, ProductoHierro[].class);
    }

    public ProductoMadera crearProductoMadera(ProductoMadera productoMadera) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(BASE_URL_MADERA, productoMadera, ProductoMadera.class);
    }

    public ProductoHierro crearProductoHierro(ProductoHierro productoHierro) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(BASE_URL_HIERRO, productoHierro, ProductoHierro.class);
    }
}
