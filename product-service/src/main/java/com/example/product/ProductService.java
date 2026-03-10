package com.example.product;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class ProductService {
    private ConcurrentHashMap<Long, Product> productStore = new ConcurrentHashMap<>();
    private AtomicLong idGenerator = new AtomicLong(1);

    public List<Product> getAllProducts() {
        return new ArrayList<>(productStore.values());
    }

    public Product getProduct(Long id) {
        return productStore.get(id);
    }

    public Product createProduct(Product product) {
        Long id = idGenerator.getAndIncrement();
        product.setId(id);
        productStore.put(id, product);
        return product;
    }

    public Product updateProduct(Product product) {
        if (productStore.containsKey(product.getId())) {
            productStore.put(product.getId(), product);
            return product;
        }
        return null;
    }

    public boolean deleteProduct(Long id) {
        return productStore.remove(id) != null;
    }
}