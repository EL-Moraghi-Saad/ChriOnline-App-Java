package com.chrionline.service;

import com.chrionline.model.Product;
import java.util.HashMap;
import java.util.Map;

public class ProductService {
    private Map<Integer, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public Product getProductById(int id) {
        return products.get(id);
    }
}