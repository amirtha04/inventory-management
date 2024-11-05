package com.emart.inventory_management.controller;

import org.springframework.web.bind.annotation.*;
import com.emart.inventory_management.model.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class InventoryController {

    private List<Product> products = new ArrayList<>();

    @PostMapping("/add")
    public String addProduct(@RequestBody Product product) {
        products.add(product);
        return "Product added successfully!";
    }

    @DeleteMapping("/remove/{id}")
    public String removeProduct(@PathVariable int id) {
        products.removeIf(product -> product.getId() == id);
        return "Product removed successfully!";
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                product.setQuantity(updatedProduct.getQuantity());
                return "Product updated successfully!";
            }
        }
        return "Product not found!";
    }

    @GetMapping("/list")
    public List<Product> listProducts() {
        return products;
    }

    @GetMapping("/search/{name}")
    public Product searchProduct(@PathVariable String name) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}

