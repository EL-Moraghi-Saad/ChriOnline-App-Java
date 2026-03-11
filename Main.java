package com.chrionline.main;

import com.chrionline.model.Cart;
import com.chrionline.model.Product;
import com.chrionline.service.ProductService;

public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        productService.addProduct(new Product(1, "Laptop", 999.99));
        productService.addProduct(new Product(2, "Mouse", 29.99));

        Cart cart = new Cart();
        cart.addProduct(1, 1);
        cart.addProduct(2, 2);

        System.out.println("Total: " + cart.calculateTotal(productService));
    }
}