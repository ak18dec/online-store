package com.github.phillipkruger.onlinestore.productrules.engine;

import com.github.phillipkruger.onlinestore.productrules.Discount;
import com.github.phillipkruger.onlinestore.productrules.Lotto;
import com.github.phillipkruger.onlinestore.productrules.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import lombok.extern.java.Log;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;

@Log
@RequestScoped
public class ProductRuleEngine {
    
    @Inject
    private KieContainer kieContainer;
    
    public Optional<Discount> getDiscount(Product product){
        List<Product> products = new ArrayList<>();
        products.add(product);
        List<Discount> discounts= getDiscounts(products);
        if(discounts.isEmpty())return Optional.empty();
        return Optional.of(discounts.get(0));
    }
    
    public List<Discount> getDiscounts(List<Product> basket){
        
        KieSession ksession = kieContainer.newKieSession();
        try {
            
            // Add product
            for(Product product : basket){
                ksession.insert(product);
            }
            // Add random lotto
            ksession.insert(Lotto.getRandomLottoInstance());
            
            // Groups
            Agenda agenda = ksession.getAgenda();
            agenda.getAgendaGroup("products").setFocus();
            agenda.getAgendaGroup("promotions").setFocus();
            
            ksession.fireAllRules();
        
            List<Discount> discounts = findDiscountsFromSession(ksession);
            
            return discounts;
        }finally{
            ksession.dispose();
        }
    }
 
    
//    private void bla(){
//        Calendar c1 = Calendar.getInstance();
//    if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || 
//        c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
//    }
    
    private List<Discount> findDiscountsFromSession(KieSession ksession){
        List<Discount> discountList = new ArrayList<>();
        Collection<?> discounts = ksession.getObjects(o -> o.getClass() == Discount.class);
        for(Object discount: discounts){
            discountList.add((Discount)discount);
        }
        return discountList;
    }
    
    
}
