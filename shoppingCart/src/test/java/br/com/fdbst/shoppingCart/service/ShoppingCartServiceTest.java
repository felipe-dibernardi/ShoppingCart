package br.com.fdbst.shoppingCart.service;

import br.com.fdbst.shoppingCart.dao.ProductDao;
import br.com.fdbst.shoppingCart.exception.ProductNotFoundException;
import br.com.fdbst.shoppingCart.exception.ProductsNotLoadedException;
import br.com.fdbst.shoppingCart.model.CommerceItem;
import br.com.fdbst.shoppingCart.model.Product;
import br.com.fdbst.shoppingCart.model.ShoppingCart;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Classe ShoppingCartServiceTest
 *
 * Essa classe implementa testes para as regras de negócio do Carrinho de compras.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public class ShoppingCartServiceTest {

    private static ShoppingCartService cartService;

    private static ProductDao productDao;

    @BeforeAll
    public static void setup() {
        cartService = new ShoppingCartService();
        productDao = new ProductDao();
        cartService.setProductDao(productDao);
    }

    @Test
    public void addItemToShoppingCart() throws ProductNotFoundException {
        ShoppingCart cart = new ShoppingCart();
        cartService.addItem(cart, "PROD0308", 2);

        assertEquals(1, cart.getItems().size());
    }

    @Test
    public void validateCartAmount() throws ProductNotFoundException, ProductsNotLoadedException {
        ShoppingCart cart = new ShoppingCart();
        CommerceItem item1 = cartService.addItem(cart, "PROD0308", 2);
        CommerceItem item2 = cartService.addItem(cart, "PROD0309", 3);

        assertEquals(item1.getAmount().add(item2.getAmount()), cart.getAmount());
    }

    @Test
    public void removeItemFromShoppingCart() throws ProductNotFoundException {
        ShoppingCart cart = new ShoppingCart();
        CommerceItem item1 = cartService.addItem(cart, "PROD0308", 2);
        CommerceItem item2 = cartService.addItem(cart, "PROD0309", 3);
        assertEquals(2, cart.getItems().size());
        cartService.removeItem(cart, item2.getId());
        assertEquals(1, cart.getItems().size());
    }

    @Test
    public void validateCartAmountAfterRemoval() throws ProductNotFoundException {
        ShoppingCart cart = new ShoppingCart();
        CommerceItem item1 = cartService.addItem(cart, "PROD0308", 2);
        CommerceItem item2 = cartService.addItem(cart, "PROD0309", 3);
        cartService.removeItem(cart, item2.getId());
        assertEquals(item1.getAmount(), cart.getAmount());
    }
}
