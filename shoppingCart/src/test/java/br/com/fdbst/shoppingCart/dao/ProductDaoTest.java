package br.com.fdbst.shoppingCart.dao;

import br.com.fdbst.shoppingCart.dao.ProductDao;
import br.com.fdbst.shoppingCart.exception.ProductsNotLoadedException;
import br.com.fdbst.shoppingCart.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Classe ProductDaoTest
 *
 * Essa classe implementa testes para o acesso aos dados de Produto.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public class ProductDaoTest {

    private static ProductDao dao;

    @BeforeAll
    public static void setup() {
        dao = new ProductDao();
    }

    @Test
    public void getAllProducts() throws ProductsNotLoadedException {
        List<Product> products = dao.getAll();
        assertNotNull(products);
    }

    @Test
    public void getProductByProductId() throws ProductsNotLoadedException {
        assertNotNull(dao.getProduct("PROD0308"));
    }

}
