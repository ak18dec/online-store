package com.github.phillipkruger.onlinestore.catalogservice;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import lombok.extern.java.Log;

@Path("/catalog")
@RequestScoped
@Log
public class CatalogApi {
    
    @GET
    public String ping(){
        return "pong";
    }
}