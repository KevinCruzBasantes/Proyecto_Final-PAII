package controller;

import models.ProductoHierro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.ProductoHierroService;

import java.util.List;

@RestController
@RequestMapping("/productos/hierro")
public class ProductoHierroController {

    private final ProductoHierroService productoHierroService;

    @Autowired
    public ProductoHierroController(ProductoHierroService productoHierroService) {
        this.productoHierroService = productoHierroService;
    }

    @PostMapping
    public ProductoHierro crearProductoHierro(@RequestBody ProductoHierro productoHierro) {
        return productoHierroService.guardarProductoHierro(productoHierro);
    }

    @GetMapping
    public List<ProductoHierro> listarProductosHierro() {
        return productoHierroService.obtenerProductosHierro();
    }

    @GetMapping("/{id}")
    public ProductoHierro obtenerProductoHierroPorId(@PathVariable Long id) {
        return productoHierroService.obtenerProductoHierroPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarProductoHierro(@PathVariable Long id) {
        productoHierroService.eliminarProductoHierro(id);
    }
}
