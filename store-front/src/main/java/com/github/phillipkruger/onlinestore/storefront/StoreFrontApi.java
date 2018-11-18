package com.github.phillipkruger.onlinestore.storefront;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.extern.java.Log;

@Path("/store-front")
@RequestScoped
@Log
@Api(value = "Store Front")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StoreFrontApi {
    
    @GET
    @ApiOperation(value = "Ping", notes = "Testing availability")
    public String ping(){
        return "pong";
    }
    
    
}