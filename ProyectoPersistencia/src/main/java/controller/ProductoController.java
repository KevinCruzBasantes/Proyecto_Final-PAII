package controller;

import models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.obtenerProductos();
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
    }

    @GetMapping("/buscar")
    public Producto obtenerProductoPorNombre(@RequestParam String nombre) {
        return productoService.obtenerProductoPorNombre(nombre);
    }
}
