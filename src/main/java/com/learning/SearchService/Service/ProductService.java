package com.learning.SearchService.Service;

import com.learning.SearchService.Model.Product;
import com.learning.SearchService.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public  ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Iterable<Product> findAllDocuments() {

        return productRepository.findAll();
    }

    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }
}
