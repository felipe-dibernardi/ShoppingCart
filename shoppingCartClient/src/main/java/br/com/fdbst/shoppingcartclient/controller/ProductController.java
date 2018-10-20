package br.com.fdbst.shoppingcartclient.controller;

import br.com.fdbst.shoppingcartclient.exception.BusinessException;
import br.com.fdbst.shoppingcartclient.model.Product;
import br.com.fdbst.shoppingcartclient.service.ProductService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Classe ProductController
 *
 * Essa classe implementa a interface entre as regras de negócio e interface gráfica para o Produto.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@ViewScoped
@Named
public class ProductController extends BaseController implements Serializable {

    @EJB
    private ProductService productService;

    private List<Product> products;

    @PostConstruct
    public void init() {
        try {
            this.products = this.productService.getProducts();
        } catch (BusinessException e) {
            this.addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
        }
    }

    /**
     * Navega para o carrinho de compras.
     * @return URL do carrinho de compras.
     */
    public String navigateToCart() {
        return "cart.jsf?faces-redirect=true";
    }

    /**
     * Recupera o nome do produto através do seu identificador.
     * @param productId Identificador do produto.
     * @return Nome do produto.
     */
    public String retrieveProductName(String productId) {
        for (Product prod : products) {
            if (productId.equals(prod.getId())) {
                return prod.getName();
            }
        }
        return "";
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
