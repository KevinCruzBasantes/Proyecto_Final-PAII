package models;

    public  abstract class Producto {
    private Long id;
    private String nombre;
    private String material;
    private int stock;
    private int cantidad;

    public Producto() {
        // Constructor vacío para serialización
    }

    public Producto(String nombre, Long materialId, int stock) {
        this.nombre = nombre;
        this.material = obtenerMaterialPorId(materialId);
        this.stock = stock;
    }
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


    protected abstract String obtenerMaterialPorId(Long id);
}
