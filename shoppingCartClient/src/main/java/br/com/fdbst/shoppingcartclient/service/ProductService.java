package br.com.fdbst.shoppingcartclient.service;

import br.com.fdbst.shoppingcartclient.exception.BusinessException;
import br.com.fdbst.shoppingcartclient.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.ejb.Stateless;
import java.io.IOException;
import java.util.List;

/**
 * Classe ProductService
 *
 * Essa classe implementa as regras de negócio para o produto.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Stateless
public class ProductService {

    private ObjectMapper mapper = new ObjectMapper();

    private String baseURL = "http://186.250.124.10:8080/cartApi/webresources/products";

    /**
     * Recupera a lista de produtos do sistema,
     * @return Lista de produtos.
     * @throws BusinessException Exceção a ser enviada caso haja algum erro na requisição.
     */
    public List<Product> getProducts() throws BusinessException {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(baseURL);
            HttpResponse response = client.execute(request);
            return mapper.readValue(response.getEntity().getContent(), new TypeReference<List<Product>>() {});
        } catch (IOException e) {
            throw new BusinessException("Erro ao recuperar lista de produtos");
        }
    }

}
