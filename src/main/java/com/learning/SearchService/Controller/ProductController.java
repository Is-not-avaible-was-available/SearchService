package com.learning.SearchService.Controller;


import com.learning.SearchService.Model.Product;
import com.learning.SearchService.Service.ElasticSearchService;
import com.learning.SearchService.Service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController{

    private final ProductService productService;
    private final ElasticSearchService elasticSearchService;

    public ProductController(ProductService productService, ElasticSearchService elasticSearchService){
        this.productService = productService;
        this.elasticSearchService = elasticSearchService;
    }

    @GetMapping("/allDocuments")
    public String findAllDocuments() throws IOException {
        return elasticSearchService.findAllDocuments();
    }


    @PostMapping("insert")
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }

    @GetMapping("/allProducts")
    public List<Product> getAllProduct() throws IOException {
        return elasticSearchService.findAllProducts();
    }

    @GetMapping("/products/{title}")
    public List<Product> getAllProductBasedOnTitle(@PathVariable String title) throws IOException {
        return elasticSearchService.findAllProductsBasedOnTitle(title);
    }

    @GetMapping("/products/fuzzy/{titleFuzzy}")
    public List<Product> getAllProductByTitleFuzzy(@PathVariable String titleFuzzy) throws IOException {
        return elasticSearchService.findAllProductBasedOnFuzzySearch(titleFuzzy);
    }

    @GetMapping("/products/bool/{title}/{qty}")
    public List<Product> boolSearchOnTitleAndQty(@PathVariable String title
            ,@PathVariable Integer qty) throws IOException {
        return elasticSearchService.boolQuery(title,qty);
    }
}
