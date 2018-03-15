/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangalo.cursoprimeavancado.controller;

import com.hangalo.cursoprimeavancado.ejb.UserFacadeLocal;
import com.hangalo.cursoprimeavancado.model.User;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author informatica
 */
@Named
@ViewScoped
public class LoginController implements Serializable {

    @EJB
    private UserFacadeLocal EJBuser;
    private User user;

    @PostConstruct
    public void init() {

        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String iniciarSessao() {
        User userLogin;
        String rediracao = null;

        try {
            userLogin = EJBuser.iniciarSessao(user);
            if (userLogin != null) {
                // Armazenar na sessao do JSF
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userLogin", userLogin);
                rediracao = "protegido/principal?faces-redirect=true";
            } else {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Credenciais incorrectas"));

            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Erro"));
        }
        return rediracao;
    }

}
