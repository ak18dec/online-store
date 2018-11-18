package com.github.phillipkruger.onlinestore.productrules;

import com.github.phillipkruger.onlinestore.productrules.engine.ProductRuleEngine;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import lombok.extern.java.Log;

@Path("/product-rules")
@RequestScoped
@Log
@Api(value = "Product Rules")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductRulesApi {
    
    @Inject
    private ProductRuleEngine productRuleEngine;
    
    @GET
    @ApiOperation(value = "Ping", notes = "Testing availability")
    public String ping(){
        return "pong";
    }
    
    @GET
    @Path("/discount/{productName}")
    public Discount getDiscount(@PathParam("productName") String productName){
        // curl -X GET --header 'Accept: application/json' 'http://localhost:8080/store-front/api/catalog/product/dehydratedboulders'
        Client client = ClientBuilder.newClient();
        Product product = client.target("http://localhost:8080/store-front/api/catalog/product/" + productName)
            .request(MediaType.APPLICATION_JSON)
            .get(Product.class);
        
        productRuleEngine.applyDiscount(product);
        
        return new Discount(new Double(25),product);
    }
}