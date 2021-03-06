package com.github.phillipkruger.onlinestore.productrules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(of = "product")
public class Discount {
    private String name;
    private Double persentage;
    private Product product;
    private Lotto lotto = Lotto.getZeroLottoInstance();
    private String group;
    
    public Discount(String name,Double persentage,Product product,String group){
        this.name = name;
        this.persentage = persentage;
        this.product = product;
        this.group = group;
    }
}
