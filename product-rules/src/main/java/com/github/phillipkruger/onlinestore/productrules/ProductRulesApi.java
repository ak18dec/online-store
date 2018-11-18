package com.github.phillipkruger.onlinestore.productrules;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import lombok.extern.java.Log;

@Path("/product-rules")
@RequestScoped
@Log
@Api(value = "Catalog service")
public class ProductRulesApi {
    
    @GET
    @ApiOperation(value = "Ping", notes = "Testing availability")
    public String ping(){
        return "pong";
    }
}