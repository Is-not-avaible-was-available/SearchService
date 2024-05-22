package com.learning.SearchService.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "eatables")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private Long id;
    private String title;
    private String description;
    private Integer price;
    private Integer quantity;

}
