package com.github.phillipkruger.onlinestore.catalogservice;

import com.github.phillipkruger.onlinestore.catalogservice.data.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.extern.java.Log;

@Path("/catalog")
@RequestScoped
@Log
@Api(value = "Catalog service")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CatalogApi {
    
    @Inject
    private ProductRepository productRepository;
    
    @GET
    @Path("/products")
    @ApiOperation(value = "Products", notes = "Get all products")
    public List<Product> getProducts(){
        return new ArrayList<>(productRepository.getCatalog().values());
    }
    
    @GET
    @Path("/product/{id}")
    @ApiOperation(value = "Product", notes = "Get products by id")
    public Product getProduct(@PathParam("id") String id){
        return productRepository.getCatalog().getOrDefault(id, null);
    }
    
    @GET
    @Produces("image/png")
    @Path("/images/{name}.png")
    @ApiOperation(value = "Image", notes = "Get the product image")
    public byte[] findImage(@PathParam("name") String name) throws IOException{
        return productRepository.getImages().get(name);
    }
}