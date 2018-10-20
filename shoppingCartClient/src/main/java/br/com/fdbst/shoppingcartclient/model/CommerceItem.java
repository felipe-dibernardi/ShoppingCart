package br.com.fdbst.shoppingcartclient.model;

import java.math.BigDecimal;

/**
 * Classe CommerceItem
 *
 * Essa classe representa o item comercializado no sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public class CommerceItem {

    private String id;

    private Integer quantity;

    private BigDecimal amount;

    private String productId;

    public CommerceItem() {
    }

    public CommerceItem(Integer quantity, Product product) {
        this.quantity = quantity;
        this.productId = product.getId();
        this.amount = product.getPrice().multiply(BigDecimal.valueOf(quantity));
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
