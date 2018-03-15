/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangalo.cursoprimeavancado.controller;

import com.hangalo.cursoprimeavancado.ejb.NotaFacadeLocal;
import com.hangalo.cursoprimeavancado.model.Nota;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author informatica
 */
@Named
@RequestScoped
public class ComentarController implements Serializable{
    
    @EJB
    private NotaFacadeLocal notaEJB;
    
    private List<Nota> notas;
    @Inject
    private Nota nota;
    
    @PostConstruct
    public void init(){
    notas = new ArrayList<>();
    notas = notaEJB.findAll();
    
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
    
    
    public void atribuir(Nota nota){
    
    this.nota = nota;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }
    
    
    
}
