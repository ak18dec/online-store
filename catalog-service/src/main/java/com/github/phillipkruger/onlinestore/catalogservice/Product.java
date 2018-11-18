package com.github.phillipkruger.onlinestore.catalogservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Product {
    
    private String id;
    private String name;
    private String description;
    private String imageName;
    private Double price;
}
