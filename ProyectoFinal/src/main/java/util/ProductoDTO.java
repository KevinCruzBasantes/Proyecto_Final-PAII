package util;

import models.Material;
import models.ProgressObserver;

public class ProductoDTO {
    private Long id;
    private String nombre;
    private String material;
    private int stock;
    private int cantidad;
    private Material materialEntity;

    // Constructor sin parámetros
    public ProductoDTO() {
    }

    public ProductoDTO(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    // Constructor con parámetros
    public ProductoDTO(Long id, String nombre, String material, int stock, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.material = material;
        this.stock = stock;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Material getMaterialEntity() {
        return materialEntity;
    }

    public void setMaterialEntity(Material materialEntity) {
        this.materialEntity = materialEntity;
    }

    public void manufacturar(ProgressObserver observer) throws InterruptedException {
        for (int i = 0; i <= 100; i += 10) {
            Thread.sleep(500); // Simula el tiempo de manufactura
            if (observer != null) {
                observer.updateProgress("Fabricación en curso", i, this);
            }
        }
    }

    @Override
    public String toString() {
        return "ProductoDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", material='" + material + '\'' +
                ", stock=" + stock +
                ", cantidad=" + cantidad +
                '}';
    }
}
