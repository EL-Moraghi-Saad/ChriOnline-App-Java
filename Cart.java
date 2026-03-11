package com.chrionline.model;

import java.util.HashMap;
import java.util.Map;
import com.chrionline.service.ProductService;

public class Cart {
    private Map<Integer, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }

    public void addProduct(int productId, int quantity) {
        if (items.containsKey(productId)) {
            int currentQuantity = items.get(productId);
            items.put(productId, currentQuantity + quantity);
        } else {
            items.put(productId, quantity);
        }
    }

    public void removeProduct(int productId) {
        items.remove(productId);
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public double calculateTotal(ProductService productService) {
        double total = 0;
        for (Map.Entry<Integer, Integer> entry : items.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();
            Product product = productService.getProductById(productId);
            if (product != null) {
                total += product.getPrice() * quantity;
            }
        }
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
