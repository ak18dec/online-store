package com.github.phillipkruger.onlinestore.storefront.controller;


import com.github.phillipkruger.onlinestore.catalogservice.CatalogApi;
import com.github.phillipkruger.onlinestore.catalogservice.Product;
import com.github.phillipkruger.onlinestore.productrules.Discount;
import com.github.phillipkruger.onlinestore.productrules.ProductRulesApi;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

/**
 * JSF 2.2
 * Controller for notes
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@RequestScoped
@Named
public class ProductsController implements Serializable {
    
    @Inject
    private CatalogApi catalogApi; 
    @Inject ProductRulesApi productRulesApi;
    
    @Getter
    private List<Product> products = null;
    
    @Getter
    private Discount discount;
    
    @PostConstruct
    public void refresh(){
        products = catalogApi.getProducts();
    }
    
    public void buyProduct(String id){
        Product product = catalogApi.getProduct(id);
        this.discount = productRulesApi.getDiscount(id);
        
        log.severe("Buying " + product + ", Getting some discount " + discount);
    }
    
    public void clear(){
        this.discount = null;
    }
    
}