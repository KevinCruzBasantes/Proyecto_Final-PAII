package services;

import models.ProductoHierro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductoHierroRepository;

import java.util.List;

@Service
public class ProductoHierroService {

    private final ProductoHierroRepository productoHierroRepository;

    @Autowired
    public ProductoHierroService(ProductoHierroRepository productoHierroRepository) {
        this.productoHierroRepository = productoHierroRepository;
    }

    public ProductoHierro guardarProductoHierro(ProductoHierro productoHierro) {
        return productoHierroRepository.save(productoHierro);
    }

    public List<ProductoHierro> obtenerProductosHierro() {
        return productoHierroRepository.findAll();
    }

    public ProductoHierro obtenerProductoHierroPorId(Long id) {
        return productoHierroRepository.findById(id).orElse(null);
    }

    public void eliminarProductoHierro(Long id) {
        productoHierroRepository.deleteById(id);
    }
}
