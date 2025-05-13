package com.example.ecommerce.mapper;

import com.example.ecommerce.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> findAll();
    Product findById(Long id);
    int insert(Product product);
    int update(Product product);
    int deleteById(Long id);
}