package com.github.phillipkruger.onlinestore.productrules.engine;

import com.github.phillipkruger.onlinestore.productrules.Product;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import lombok.extern.java.Log;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

@Log
@RequestScoped
public class ProductRuleEngine {
    
    @Inject
    private KieContainer kieContainer;
    
    public void applyDiscount(Product product){
        List<Product> products = new ArrayList<>();
        products.add(product);
        applyDiscount(products);
    }
    
    public void applyDiscount(List<Product> basket){
        KieSession ksession = kieContainer.newKieSession();
        
        for(Product product : basket){
            ksession.insert(product);
        }
        
        ksession.fireAllRules();
        ksession.dispose();
    }
 
    
    
}
