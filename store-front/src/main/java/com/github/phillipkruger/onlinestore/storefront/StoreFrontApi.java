package com.github.phillipkruger.onlinestore.storefront;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import lombok.extern.java.Log;

@Path("/store-front")
@RequestScoped
@Log
@Api(value = "Store Front Api")
public class StoreFrontApi {
    
    @GET
    @ApiOperation(value = "Ping", notes = "Testing availability")
    public String ping(){
        return "pong";
    }
}