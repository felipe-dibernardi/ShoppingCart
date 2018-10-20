package br.com.fdbst.shoppingcartclient.exception;

/**
 * Classe BusinessException
 *
 * Essa classe representa a exceção de regra de negócio no sistema,
 *
 * @author Felipe Di Bernardi S Thiago
 */
public class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }
}
