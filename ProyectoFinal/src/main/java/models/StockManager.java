package models;

import java.util.HashMap;
import java.util.Map;

public class StockManager {
    private Map<String, Integer> stock;

    public StockManager() {
        stock = new HashMap<>();
        stock.put("madera", 100);
        stock.put("hierro", 100);
    }

    public boolean consumirMaterial(String material, int cantidad) {
        int stockActual = stock.getOrDefault(material, 0);
        if (stockActual >= cantidad) {
            stock.put(material, stockActual - cantidad);
            return true;
        }
        return false;
    }

    public int getStock(String material) {
        return stock.getOrDefault(material, 0);
    }

    public void reponerMaterial(String material, int cantidad) {
        stock.put(material, stock.getOrDefault(material, 0) + cantidad);
    }
}
