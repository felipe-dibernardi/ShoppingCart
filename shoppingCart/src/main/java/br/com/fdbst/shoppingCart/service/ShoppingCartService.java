package br.com.fdbst.shoppingCart.service;

import br.com.fdbst.shoppingCart.dao.ProductDao;
import br.com.fdbst.shoppingCart.exception.ProductNotFoundException;
import br.com.fdbst.shoppingCart.exception.ProductsNotLoadedException;
import br.com.fdbst.shoppingCart.model.CommerceItem;
import br.com.fdbst.shoppingCart.model.Product;
import br.com.fdbst.shoppingCart.model.ShoppingCart;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Classe ShoppingCartService
 *
 * Essa classe implementa as regras de negócio para o Carrinho de Compra.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Stateless
public class ShoppingCartService {

    @EJB
    private ProductDao productDao;

    /**
     * Adiciona um item ao Carrinho de Compras.
     * @param cart Carrinho de Compras.
     * @param productId Produto adicionado.
     * @param quantity Quantidade do produto adicionado.
     */
    public CommerceItem addItem(ShoppingCart cart, String productId, Integer quantity) throws ProductNotFoundException {
        try {
            Product product = this.productDao.getProduct(productId);

            CommerceItem item = new CommerceItem(quantity, product);

            if (cart.getItems() == null) {
                cart.setItems(new ArrayList<>());
            }
            cart.getItems().add(item);

            BigDecimal amount = cart.getAmount().add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));

            cart.setAmount(amount);

            return item;
        } catch (ProductsNotLoadedException e) {
            throw new ProductNotFoundException("Produto não encontrado");
        }

    }

    /**
     * Remove um item do Carrinho de Compras.
     * @param cart Carrinho de Compras.
     * @param itemId Id do item a ser removido.
     */
    public void removeItem(ShoppingCart cart, String itemId) {
        for (CommerceItem item: cart.getItems()) {
            if (item.getId().equals(itemId)) {
                cart.setAmount(cart.getAmount().subtract(item.getAmount()));
                cart.getItems().remove(item);
                break;
            }
        }
    }

    /**
     * Método para inserção de uma instância de ProdutoDAO apenas para fins de testes.
     * @param dao ProdutoDAO a ser inserido.
     */
    protected void setProductDao(ProductDao dao) {
        this.productDao = dao;
    }

}
