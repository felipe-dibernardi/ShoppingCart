package br.com.fdbst.shoppingCart.dao;

import br.com.fdbst.shoppingCart.exception.ProductsNotLoadedException;
import br.com.fdbst.shoppingCart.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Stateless;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Classe ProductDao
 *
 * Essa classe implementa o acesso aos dados da entidade Produto.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Stateless
public class ProductDao {

    /**
     * Busca todos os produtos do sistema.
     * @return Lista de Produtos.
     * @throws IOException Exceção a ser lançada caso haja erro na leitura do arquivo contendo os produtos.
     */
    public List<Product> getAll() throws ProductsNotLoadedException {
        try {
            ObjectMapper productMapper = new ObjectMapper();
            File productsFile = new File("/home/felipe/products.json");
            return productMapper.readValue(productsFile, new TypeReference<List<Product>>(){});
        } catch (IOException e) {
            throw new ProductsNotLoadedException("Não foi possível carregar os produtos.");
        }

    }

    /**
     * Busca um produto pelo seu id.
     * @param id Id do produto desejado.
     * @return Produto desejado.
     * @throws IOException Exceção a ser lançada caso haja erro na leitura do arquivo contendo os produtos.
     */
    public Product getProduct(String id) throws ProductsNotLoadedException {
        Product product = null;

        for (Product prod: this.getAll()) {
            if (prod.getId().equals(id)) {
                product = prod;
                break;
            }
        }
        return product;
    }

}
