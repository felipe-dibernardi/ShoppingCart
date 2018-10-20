package br.com.fdbst.shoppingCart.exception;

/**
 * Classe ProductsNotLoadedException
 *
 * Essa classe representa o erro de falha ao carregar produtos no sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public class ProductsNotLoadedException extends Exception {

    private String message;

    public ProductsNotLoadedException() {
    }

    public ProductsNotLoadedException(String message) {
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
