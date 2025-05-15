package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.mapper.ProductMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;
    private final ProductMapper productMapper;
    private final ProductService productService;

    public RedisService(RedisTemplate<String, String> redisTemplate, ProductMapper productMapper, ProductService productService) {
        this.redisTemplate = redisTemplate;
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @Transactional
    public int deductStock(String key) {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setLocation(new ClassPathResource("lua/stock_deduct.lua"));
        script.setResultType(Long.class);

        Long result = redisTemplate.execute(script, Collections.singletonList(key));
        return result != null ? result.intValue() : -999;
    }

    @PostConstruct
    public void loadAllStocksOnStartup() {
        List<Product> productList = productMapper.findAll();
        for (Product product : productList) {
            String key = "stock:" + product.getId();
            redisTemplate.opsForValue().set(key, String.valueOf(product.getStock()));
        }
    }
}
