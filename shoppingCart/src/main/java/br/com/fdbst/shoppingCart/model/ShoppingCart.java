package br.com.fdbst.shoppingCart.model;

import javax.enterprise.inject.Model;
import java.math.BigDecimal;
import java.util.List;

/**
 * Classe ShoppingCart
 *
 * Essa classe representa um carrinho de compras no sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Model
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
