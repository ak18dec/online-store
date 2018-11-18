package com.github.phillipkruger.onlinestore.productrules;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import lombok.extern.java.Log;

@Path("/product-rules")
@RequestScoped
@Log
public class ProductRulesApi {
    
    @GET
    public String ping(){
        return "pong";
    }
}