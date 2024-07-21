package com.practice.microservice.spring_boot_mongodb_docker;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductServiceTests {

    private ProductService productService;

    @Before
    public void setup() {
        productService = new ProductService();
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertNotNull(products);
        assertEquals(3, products.size());
    }

    @Test
    public void testGetProductById() {
        Optional<Product> product = productService.getProduct("1");
        assertTrue(product.isPresent());
        assertEquals("Product 1", product.get().getName());
    }

    @Test
    public void testGetProductByIdNotFound() {
        Optional<Product> product = productService.getProduct("4");
        assertFalse(product.isPresent());
    }

    @Test
    public void testAddProduct() {
        Product newProduct = new Product("4","Product", "Product 4", 14.99,"Cat 1");
        productService.addProduct(newProduct);
        List<Product> products = productService.getAllProducts();
        assertEquals(4, products.size());
        assertTrue(products.contains(newProduct));
    }

    @Test
    public void testUpdateProduct() {
        Product updatedProduct = new Product("4","Product", "Product 4", 14.99,"Cat 1");
        productService.updateProduct("1", updatedProduct);
        Optional<Product> product = productService.getProduct("1");
        assertTrue(product.isPresent());
        assertEquals("Updated Product 1", product.get().getName());
    }

    @Test
    public void testDeleteProduct() {
        productService.deleteProduct("1");
        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
        assertFalse(products.contains(new Product("4","Product", "Product 4", 14.99,"Cat 1")));
    }
}