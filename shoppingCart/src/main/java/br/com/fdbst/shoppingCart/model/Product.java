package br.com.fdbst.shoppingCart.model;

import javax.enterprise.inject.Model;
import java.math.BigDecimal;

/**
 * Classe Product
 *
 * Essa classe representa um produto no sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Model
public class Product {

    private String id;

    private String name;

    private String image;

    private BigDecimal price;

    public Product() {
    }

    public Product(String id, String name, String image, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
