package br.com.fdbst.shoppingCart.exception;

/**
 * Classe ProductNotFoundException
 *
 * Essa classe representa o erro de produto não encontrado no sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public class ProductNotFoundException extends Exception {

    private String message;

    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
