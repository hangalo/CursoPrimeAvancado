/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangalo.cursoprimeavancado.controller;

import com.hangalo.cursoprimeavancado.ejb.NotaFacadeLocal;
import com.hangalo.cursoprimeavancado.model.Nota;
import java.io.Serializable;
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
public class AvaliarController implements Serializable{
    
    @EJB
    private NotaFacadeLocal notaEJB;
    @Inject
    private ComentarController comentarController;
    @Inject
    private Nota nota;
    
    @PostConstruct
    public void init(){
    this.nota = comentarController.getNota();
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }
    
    
    public void registar(){
        try {
             notaEJB.edit(nota);
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save", "Dados guardados com sucesso"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Save", "Erro ao guardar comentario"));
        }finally{
        
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        
        }
    }
    
    
    
}
