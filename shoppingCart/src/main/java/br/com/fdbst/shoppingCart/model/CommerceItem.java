package br.com.fdbst.shoppingCart.model;

import javax.enterprise.inject.Model;
import java.math.BigDecimal;

/**
 * Classe CommerceItem
 *
 * Essa classe representa um Item no carrinho no sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Model
public class CommerceItem {

    private static int accumulatedId = 0;

    private String id;

    private Integer quantity;

    private BigDecimal amount;

    private String productId;

    public CommerceItem() {
    }

    public CommerceItem(Integer quantity, Product product) {
        this.generateId();
        this.quantity = quantity;
        this.productId = product.getId();
        this.amount = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    /**
     * Gera um Id único baseado no Id acumulado
     * @return Id único gerado.
     */
    private void generateId() {
        accumulatedId++;
        this.id = accumulatedId + "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
