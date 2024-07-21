package util;

import models.ProductoMadera;
import models.ProductoHierro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InicializadorDeProductos {

    private final ProductoAPI productoAPI;

    @Autowired
    public InicializadorDeProductos(ProductoAPI productoAPI) {
        this.productoAPI = productoAPI;
    }

    public void inicializar() {
        // Crear productos de madera
        ProductoMadera sillaMadera = new ProductoMadera("Silla de Madera", 5);
        ProductoMadera mesaMadera = new ProductoMadera("Mesa de Madera", 5);
        ProductoMadera estanteriaMadera = new ProductoMadera("Estantería de Madera", 5);
        ProductoMadera armario= new ProductoMadera("Armario madera", 30);

        // Crear productos en el proyecto de persistencia
        productoAPI.crearProductoMadera(sillaMadera);
        productoAPI.crearProductoMadera(mesaMadera);
        productoAPI.crearProductoMadera(estanteriaMadera);
        productoAPI.crearProductoMadera(armario);

        // Crear productos de hierro
        ProductoHierro sillaHierro = new ProductoHierro("Silla de Hierro", 5);
        ProductoHierro mesaHierro = new ProductoHierro("Mesa de Hierro", 5);
        ProductoHierro estanteriaHierro = new ProductoHierro("Estantería de Hierro", 5);

        // Crear productos en el proyecto de persistencia
        productoAPI.crearProductoHierro(sillaHierro);
        productoAPI.crearProductoHierro(mesaHierro);
        productoAPI.crearProductoHierro(estanteriaHierro);
    }
}
