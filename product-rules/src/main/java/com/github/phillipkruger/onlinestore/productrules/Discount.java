package com.github.phillipkruger.onlinestore.productrules;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Discount {
    
    private Double persentage;
    private Product product;
}
