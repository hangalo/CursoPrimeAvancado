/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangalo.cursoprimeavancado.controller;

import com.hangalo.cursoprimeavancado.model.User;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author informatica
 */
@Named
@ViewScoped
public class PaginaController implements Serializable{
    public void verificarSessao(){
        FacesContext context=FacesContext.getCurrentInstance();
        try {
            User user = (User)context.getExternalContext().getSessionMap().get("userLogin");
            if(user == null){
            
            context.getExternalContext().redirect("permicoes.xhtml");
            
            }
        } catch (Exception e) {
            
            //log para guardar registo de erro
        }
    }
}
