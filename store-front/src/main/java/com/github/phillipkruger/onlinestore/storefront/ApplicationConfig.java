package com.github.phillipkruger.onlinestore.storefront;

import com.github.phillipkruger.apiee.ApieeService;
import com.github.phillipkruger.onlinestore.catalogservice.CatalogApi;
import com.github.phillipkruger.onlinestore.productrules.ProductRulesApi;
import io.swagger.annotations.SwaggerDefinition;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RS 2.0
 * 
 * Demonstrate how to activate JAX-RS. 
 * All REST Endpoints available under /api
 * 
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@SwaggerDefinition(basePath = "/api")
@ApplicationPath("/api")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(ProductRulesApi.class);
        classes.add(CatalogApi.class);
        classes.add(ApieeService.class);
        return classes;
    }
}
