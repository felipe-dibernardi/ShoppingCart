package br.com.fdbst.shoppingCart.ws;

import br.com.fdbst.shoppingCart.exception.ProductNotFoundException;
import br.com.fdbst.shoppingCart.model.CommerceItem;
import br.com.fdbst.shoppingCart.model.ShoppingCart;
import br.com.fdbst.shoppingCart.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.Serializable;

/**
 * Classe ShoppingCartWS
 *
 * Essa classe implementa os EndPoints para a entidade Carrinho de Compras.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Api(value = "Shopping Cart")
@SessionScoped
@Path("shoppingcart")
public class ShoppingCartWS implements Serializable {

    private ShoppingCart cart = new ShoppingCart();

    @EJB
    private ShoppingCartService cartService;

    /**
     * Busca o carrinho da sessão do usuário.
     * @return Carrinho de Compras.
     */
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Carrinho recuperado com sucesso"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Erro ao recuperar Carrinho"
            )}
    )
    @ApiOperation(httpMethod = "GET", value = "Busca o Carrinho de compras na sessão", response = ShoppingCart.class)
    @GET
    @Produces("application/json")
    public Response getCart() {
        return Response.ok().entity(cart).build();
    }

    /**
     * Adiciona um Item no Carrinho de Compras.
     * @param productId Id do Produto adicionado.
     * @param quantity Quantidade do produto.
     * @return Item adicionado ou mensagem de erro na adição.
     */
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Item adicionado ao carrinho com sucesso"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Erro ao adicionar item ao carrinho"
            )}
    )
    @ApiOperation(httpMethod = "POST", value = "Adiciona um item ao carrinho", response = CommerceItem.class)
    @POST
    @Path("items")
    @Produces("application/json")
    public Response addItem(@QueryParam("product_id") String productId, @QueryParam("quantity") int quantity) {
        try {
            CommerceItem item = cartService.addItem(cart, productId, quantity);
            return Response.ok().entity(item).build();
        } catch (ProductNotFoundException e) {
            return Response.serverError().entity(e).build();
        }
    }

    /**
     * Remove um item do Carrinho de Compras através do Id.
     * @param itemId Id do item a ser removido.
     * @return Resposta enviada após a remoção.
     */
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Item removido do carrinho com sucesso"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Erro ao remover item do carrinho"
            )}
    )
    @ApiOperation(httpMethod = "DELETE", value = "Remove um item do carrinho", response = CommerceItem.class)
    @DELETE
    @Path("items/{id}")
    public Response removeItem(@PathParam("id") String itemId) {
        this.cartService.removeItem(cart, itemId);
        return Response.ok().build();
    }


}
