package br.com.fdbst.shoppingCart.ws;

import br.com.fdbst.shoppingCart.dao.ProductDao;
import br.com.fdbst.shoppingCart.exception.ProductsNotLoadedException;
import br.com.fdbst.shoppingCart.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Classe ProductWS
 *
 * Essa classe implementa os Endpoints para a entidade Produto.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Api(value = "Produtos")
@Stateless
@Path("products")
public class ProductWS {

    @EJB
    private ProductDao productDao;

    /**
     * Busca todos os produtos.
     * @return Produtos do sistema ou mensagem de erro ao buscar produtos.
     */
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Produtos listados com sucesso"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Falha ao listar produtos"
            )
    })
    @ApiOperation(httpMethod = "GET", value = "Lista todos os produtos disponíveis", response = Product.class, responseContainer = "List")
    @GET
    @Produces("application/json")
    public Response getProducts() {
        try {
            return Response.ok().entity(new GenericEntity<List<Product>>(this.productDao.getAll()){}).build();
        } catch (ProductsNotLoadedException e) {
            return Response.serverError().entity(e).build();
        }

    }

}
