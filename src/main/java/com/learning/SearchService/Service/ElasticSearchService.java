package com.learning.SearchService.Service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.learning.SearchService.Model.Product;
import com.learning.SearchService.Util.ElasticSearchUtil;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class ElasticSearchService {

    private final ElasticsearchClient elasticsearchClient;
    public ElasticSearchService(ElasticsearchClient elasticsearchClient){
        this.elasticsearchClient = elasticsearchClient;
    }

    public String findAllDocuments() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.matchAllQuerySupplier();

        SearchResponse<Map> searchResponse = elasticsearchClient
                .search(s -> s
                        .query(supplier.get()), Map.class);
        System.out.println("Query executed: " + supplier.get().toString());
        List<Hit<Map>> hitList = searchResponse.hits().hits();
        return hitList.toString();
    }

    public List<Product> findAllProducts() throws IOException{
        Supplier<Query> supplier = ElasticSearchUtil.matchAllProductQuerySupplier();
        SearchResponse<Product> searchResponse = elasticsearchClient
                .search(s -> s.index("eatables").query(supplier.get()), Product.class);
        List<Hit<Product>> hitList = searchResponse.hits().hits();
        System.out.println("Query executed: " + supplier.get().toString());
        List<Product> products = new ArrayList<>();
        for(Hit<Product> hit: hitList){
            Product product = (Product) hit.source();
            products.add(product);
        }
        return products;
    }

    public List<Product> findAllProductsBasedOnTitle(String title) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.matchQuerySupplier(title);
        SearchResponse<Product> searchResponse= elasticsearchClient
                .search(s -> s.index("eatables").query(supplier.get()), Product.class);
        System.out.println("Query executed: " + supplier.get().toString());
        List<Hit<Product>> hitList = searchResponse.hits().hits();
        List<Product> products = new ArrayList<>();

        for(Hit<Product> hit: hitList){
            Product product = (Product) hit.source();
            products.add(product);
        }

        return products;
    }

    public List<Product> findAllProductBasedOnFuzzySearch(String fieldValue) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.fuzzyQuerySupplier(fieldValue);
        SearchResponse<Product> searchResponse = elasticsearchClient
                .search(s->s.index("eatables").query(supplier.get()), Product.class);
        System.out.println("Query executed: " + supplier.get().toString());
        List<Hit<Product>> hitList = searchResponse.hits().hits();
        List<Product> products = new ArrayList<>();
        for(Hit<Product> hit: hitList){
            Product product = (Product) hit.source();
            products.add(product);
        }
        return products;
    }

    public List<Product> boolQuery(String title, Integer qty) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplierBoolQuery(title, qty);
        SearchResponse<Product> searchResponse = elasticsearchClient
                .search(s->s.index("eatables").query(supplier.get()), Product.class);

        List<Hit<Product>> hitList = searchResponse.hits().hits();
        List<Product> products = new ArrayList<>();
        for(Hit<Product> hit: hitList){
            Product product = (Product) hit.source();
            products.add(product);
        }
        return products;
    }
}
