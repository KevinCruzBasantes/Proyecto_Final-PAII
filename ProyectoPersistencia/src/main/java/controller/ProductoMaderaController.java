package controller;

import models.ProductoMadera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.ProductoMaderaService;

import java.util.List;

@RestController
@RequestMapping("/productos/madera")
public class ProductoMaderaController {

    private final ProductoMaderaService productoMaderaService;

    @Autowired
    public ProductoMaderaController(ProductoMaderaService productoMaderaService) {
        this.productoMaderaService = productoMaderaService;
    }

    @PostMapping
    public ProductoMadera crearProductoMadera(@RequestBody ProductoMadera productoMadera) {
        return productoMaderaService.guardarProductoMadera(productoMadera);
    }

    @GetMapping
    public List<ProductoMadera> listarProductosMadera() {
        return productoMaderaService.obtenerProductosMadera();
    }

    @GetMapping("/{id}")
    public ProductoMadera obtenerProductoMaderaPorId(@PathVariable Long id) {
        return productoMaderaService.obtenerProductoMaderaPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarProductoMadera(@PathVariable Long id) {
        productoMaderaService.eliminarProductoMadera(id);
    }
}
