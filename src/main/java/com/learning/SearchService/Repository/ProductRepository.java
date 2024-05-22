package com.learning.SearchService.Repository;

import com.learning.SearchService.Model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Product, Long> {
}
