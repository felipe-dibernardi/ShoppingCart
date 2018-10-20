package br.com.fdbst.shoppingCart.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Classe CORSRequestFilter
 *
 * Essa classe implementa o filtro para requisições.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Provider
public class CORSRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if (containerRequestContext.getRequest().getMethod().equals("OPTIONS")) {

            containerRequestContext.abortWith(Response.status(Response.Status.OK).build());
        }
    }
}
