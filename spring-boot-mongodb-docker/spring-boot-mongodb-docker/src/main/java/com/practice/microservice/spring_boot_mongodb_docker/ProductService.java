package com.practice.microservice.spring_boot_mongodb_docker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private ProductRepository productRepository;

    private List<Product> products = new ArrayList<>();


    public List<Product> getAllProducts() {
        messageProducer.sendMessage("Get all Products");
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(String id) {
        messageProducer.sendMessage("Get 1 Product");
        return productRepository.findById(id);
    }

    public void addProduct(Product product) {
        messageProducer.sendMessage("Save Product");
        productRepository.insert(product);
    }

    public Optional<Product> updateProduct(String id, Product product) {
        Optional<Product> existingProduct = getProduct(id);
        existingProduct.ifPresent(value -> productRepository.insert(value));
        messageProducer.sendMessage("Update Products");
        return existingProduct;
    }

    public void deleteProduct(String id) {
        Optional<Product> product = getProduct(id);
        product.ifPresent(value -> productRepository.delete(product.get()));
        messageProducer.sendMessage("Delete Products");
    }
}