package services;

import models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductoRepository;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto guardarProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getMaterial() == null) {
            throw new IllegalArgumentException("El nombre y el material son obligatorios.");
        }

        // Verificar si el producto ya existe
        Producto existente = productoRepository.findByNombre(producto.getNombre()).stream().findFirst().orElse(null);
        if (existente != null) {
            // Actualizar stock del producto existente
            existente.setStock(existente.getStock() + producto.getStock());
            return productoRepository.save(existente);
        }

        return productoRepository.save(producto);
    }

    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public Producto obtenerProductoPorNombre(String nombre) {
        return productoRepository.findByNombre(nombre).stream().findFirst().orElse(null);
    }
}
