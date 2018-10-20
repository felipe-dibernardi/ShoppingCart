package br.com.fdbst.shoppingcartclient.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Classe ShoppingCart
 *
 * Essa classe representa o Carrinho de Compras no sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public class ShoppingCart {

    private List<CommerceItem> items;

    private BigDecimal amount = BigDecimal.ZERO;

    public List<CommerceItem> getItems() {
        return items;
    }

    public void setItems(List<CommerceItem> items) {
        this.items = items;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
