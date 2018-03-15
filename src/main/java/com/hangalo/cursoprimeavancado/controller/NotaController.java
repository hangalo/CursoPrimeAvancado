/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangalo.cursoprimeavancado.controller;

import com.hangalo.cursoprimeavancado.ejb.CategoriaFacadeLocal;
import com.hangalo.cursoprimeavancado.ejb.NotaFacadeLocal;
import com.hangalo.cursoprimeavancado.model.Categoria;
import com.hangalo.cursoprimeavancado.model.Nota;
import com.hangalo.cursoprimeavancado.model.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author informatica
 */
@Named
@ViewScoped
public class NotaController implements Serializable{
    @EJB
    private NotaFacadeLocal notaEJB;
    
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
    
    
    @Inject
    private Nota nota;
    
    @Inject
    private Categoria categoria;
    
    @Inject
    private User user;
    
    private List<Categoria> categorias;
    
    @PostConstruct
    public void init(){
    categorias = categoriaEJB.findAll();
    
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
     public void save(){
         try {
               User us = (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLogin");
     nota.setUser(us);
     nota.setCategoria(categoria);
     notaEJB.create(nota);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save", "Dados guardados com sucesso"));
     
         } catch (Exception e) {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", " Erro ao guardados dados"));
         }
     }
}
