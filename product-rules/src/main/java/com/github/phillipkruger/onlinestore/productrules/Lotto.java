package com.github.phillipkruger.onlinestore.productrules;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import lombok.Data;

@Data
public class Lotto {

    private Integer percentage;
    
    private Lotto(Integer percentage){
        this.percentage = percentage;
    }
    
    public static Lotto getRandomLottoInstance(){
        List<Integer> options = Arrays.asList(0,0,1,0,0,2,0,0,3,0,0,4,0,0,5,0,0,0,6,0,0,0,7,0,0,0,8,0,0,0,9,0,0,0,10,0);
        Random rand = new Random();
        Integer p = options.get(rand.nextInt(options.size()));
        return new Lotto(p);
    }
    
    public static Lotto getZeroLottoInstance(){
        return new Lotto(0);
    }
}
