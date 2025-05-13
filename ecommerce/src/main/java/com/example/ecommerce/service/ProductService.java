package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.mapper.ProductMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductMapper mapper;

    public ProductService(ProductMapper mapper) {
        this.mapper = mapper;
    }

    public List<Product> listAll() {
        return mapper.findAll();
    }
    @Cacheable(value = "product", key = "#id")
    public Product getById(Long id) {
        return mapper.findById(id);
    }

    @CacheEvict(value = "product", key = "#product.id", condition = "#product.id != null")
    public Product save(Product product) {
        if(product.getId() == null) {
            mapper.insert(product);
        } else {
            mapper.update(product);
        }
        return product;
    }

    @CacheEvict(value = "product", key = "#id")
    public void delete(Long id) {
        mapper.deleteById(id);
    }
}
