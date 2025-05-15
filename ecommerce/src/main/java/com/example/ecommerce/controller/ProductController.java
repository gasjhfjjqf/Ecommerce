package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.RedisService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;
    private final RedisService redisService;

    public ProductController(ProductService service, RedisService redisService) {
        this.service = service;
        this.redisService = redisService;
    }

    @GetMapping
    public List<Product> getAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.save(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return service.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/{id}/deduct")
    public String deduct(@PathVariable Long id) {
        String redisKey = "stock:" + id;
        int result = redisService.deductStock(redisKey);

        return switch (result) {
            case 0 -> "Insufficient inventory, deduction failed";
            case -1 -> "An error occurred while deducting inventory";
            default -> "Inventory deducted successfully, remaining inventory: " + result;
        };
    }
}
