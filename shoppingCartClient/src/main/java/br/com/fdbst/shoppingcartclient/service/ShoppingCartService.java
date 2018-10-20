package br.com.fdbst.shoppingcartclient.service;

import br.com.fdbst.shoppingcartclient.exception.BusinessException;
import br.com.fdbst.shoppingcartclient.model.CommerceItem;
import br.com.fdbst.shoppingcartclient.model.Product;
import br.com.fdbst.shoppingcartclient.model.ShoppingCart;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.ejb.Stateless;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Classe ShoppingCartService
 *
 * Essa classe implementa a comunicação com a API de integração para o carrinho de compras
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Stateless
public class ShoppingCartService {

    private ObjectMapper mapper = new ObjectMapper();

    private String baseURL = "http://186.250.124.10:8080/cartApi/webresources/shoppingcart/";

    /**
     * Busca o carrinho de compras da sessão.
     * @return Carrinho de compras.
     */
    public ShoppingCart getShoppingCart() throws BusinessException {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(baseURL);
            HttpResponse response = client.execute(request);
            return mapper.readValue(response.getEntity().getContent(), ShoppingCart.class);
        } catch (IOException e) {
            throw new BusinessException("Erro ao buscar carrinho de compras");
        }

    }

    /**
     * Insere um produto no carrinho.
     * @param product Produto a ser insertido.
     * @return Item de carrinho.
     */
    public CommerceItem insertItem(Product product) throws BusinessException {
        try {
            HttpClient client = new DefaultHttpClient();
            URIBuilder uri = new URIBuilder(baseURL + "items");
            uri.addParameter("product_id", product.getId());
            uri.addParameter("quantity", String.valueOf(product.getQuantity()));
            HttpPost request = new HttpPost(uri.build());
            HttpResponse response = client.execute(request);


            return mapper.readValue(response.getEntity().getContent(), CommerceItem.class);
        } catch (URISyntaxException | IOException e) {
            throw new BusinessException("Erro ao inserir item");
        }
    }

    /**
     * Remove um item do carrinho.
     * @param item Item a ser removido.
     */
    public void removeItem(CommerceItem item) throws BusinessException {
        try {
            HttpClient client = new DefaultHttpClient();
            URIBuilder uri = new URIBuilder(baseURL + "items");
            uri.addParameter("id", item.getId());
            HttpDelete request = new HttpDelete(uri.build());
            HttpResponse response = client.execute(request);
        } catch (URISyntaxException | IOException e) {
            throw new BusinessException("Erro ao remover item");
        }
    }


}
