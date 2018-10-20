package br.com.fdbst.shoppingcartclient.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Classe BaseController
 *
 * Essa classe implementa métodos comuns aos controllers.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public class BaseController {

    /**
     * Adiciona mensagens no contexto corrente do usuario.
     *
     * @param severity Define e severidade da mensagem.
     * @param msg Informa a mensagem.
     */
    public void addMessage(final FacesMessage.Severity severity, final String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severity, msg, msg));

        context.getExternalContext().getFlash().setKeepMessages(true);
    }
}
