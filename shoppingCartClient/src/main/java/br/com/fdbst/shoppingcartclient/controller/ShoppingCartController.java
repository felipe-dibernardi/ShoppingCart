package br.com.fdbst.shoppingcartclient.controller;

import br.com.fdbst.shoppingcartclient.exception.BusinessException;
import br.com.fdbst.shoppingcartclient.model.CommerceItem;
import br.com.fdbst.shoppingcartclient.model.Product;
import br.com.fdbst.shoppingcartclient.model.ShoppingCart;
import br.com.fdbst.shoppingcartclient.service.ShoppingCartService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Classe ShoppingCartController
 *
 * Essa classe realiza a interface entre as regras de negócio e a interface gráfica para o Carrinho de Compras.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@SessionScoped
@Named
public class ShoppingCartController extends BaseController implements Serializable {

    @EJB
    private ShoppingCartService shoppingCartService;

    private ShoppingCart cart;

    @PostConstruct
    public void init() {
        this.getShoppingCart();
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }
    }

    /**
     * Navega para os produtos.
     * @return URL de produtos.
     */
    public String navigateToProducts() {
        return "products.jsf?faces-redirect=true";
    }

    /**
     * Recupera o carrinho de compras da sessão.
     */
    public void getShoppingCart() {
        try {
            this.cart = this.shoppingCartService.getShoppingCart();
        } catch (BusinessException e) {
            this.addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
        }
    }

    /**
     * Insere um item no carrinho.
     * @param product Produto a ser adicionado.
     */
    public void insertItem(Product product) {
        try {
            if (product.getQuantity() != 0) {
                CommerceItem item = this.shoppingCartService.insertItem(product);
                this.cart.getItems().add(item);
                this.cart.setAmount(this.cart.getAmount().add(item.getAmount()));
                this.addMessage(FacesMessage.SEVERITY_INFO, "Item adicionado com sucesso");
            } else {
                this.addMessage(FacesMessage.SEVERITY_WARN, "Não foi possível adicionar item. Quantidade não poder ser 0");
            }
        } catch (BusinessException e) {
            this.addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
        }

    }

    /**
     * Remove um item do carrinho.
     * @param item Item a ser removido.
     */
    public void removeItem(CommerceItem item) {
        try {
            this.shoppingCartService.removeItem(item);
            this.cart.getItems().remove(item);
            this.cart.setAmount(this.cart.getAmount().subtract(item.getAmount()));
            this.addMessage(FacesMessage.SEVERITY_INFO, "Item removido com sucesso");
        } catch (BusinessException e) {
            this.addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
        }
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }
}
