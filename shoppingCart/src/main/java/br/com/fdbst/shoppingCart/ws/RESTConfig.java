package br.com.fdbst.shoppingCart.ws;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe RESTConfig
 *
 * Essa classe implementa as configurações necessárias para deploy dos endpoints.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@ApplicationPath("webresources")
public class RESTConfig extends Application {

    public RESTConfig() {
        BeanConfig conf = new BeanConfig();
        conf.setTitle("ShoppingCart API");
        conf.setDescription("API RESTful para o projeto ShoppingCart");
        conf.setVersion("1.0.0-SNAPSHOT");
        conf.setHost("186.250.124.10:8080");
        conf.setBasePath("/cartApi/webresources");
        conf.setSchemes(new String[]{"http"});
        conf.setResourcePackage("br.com.fdbst.shoppingCart");
        conf.setScan(true);

    }

    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(ProductWS.class);
        resources.add(ShoppingCartWS.class);

        resources.add(ApiListingResource.class);
        resources.add(SwaggerSerializers.class);
        return resources;
    }

}
