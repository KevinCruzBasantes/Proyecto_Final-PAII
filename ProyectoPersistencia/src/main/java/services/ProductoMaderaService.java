package services;

import models.ProductoMadera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductoMaderaRepository;

import java.util.List;

@Service
public class ProductoMaderaService {

    private final ProductoMaderaRepository productoMaderaRepository;

    @Autowired
    public ProductoMaderaService(ProductoMaderaRepository productoMaderaRepository) {
        this.productoMaderaRepository = productoMaderaRepository;
    }

    public ProductoMadera guardarProductoMadera(ProductoMadera productoMadera) {
        return productoMaderaRepository.save(productoMadera);
    }

    public List<ProductoMadera> obtenerProductosMadera() {
        return productoMaderaRepository.findAll();
    }

    public ProductoMadera obtenerProductoMaderaPorId(Long id) {
        return productoMaderaRepository.findById(id).orElse(null);
    }

    public void eliminarProductoMadera(Long id) {
        productoMaderaRepository.deleteById(id);
    }
}
