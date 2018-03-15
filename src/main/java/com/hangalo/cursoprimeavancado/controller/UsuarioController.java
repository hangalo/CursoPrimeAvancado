/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangalo.cursoprimeavancado.controller;

import com.hangalo.cursoprimeavancado.ejb.UsuarioFacadeLocal;
import com.hangalo.cursoprimeavancado.model.Pessoa;
import com.hangalo.cursoprimeavancado.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author informatica
 */


@Named
@ViewScoped
public class UsuarioController implements Serializable{
    @EJB
    UsuarioFacadeLocal facadeLocal;
    private Usuario usuario;
    private Pessoa pessoa;
    
    @PostConstruct
    public void init(){
    usuario = new Usuario();
    pessoa = new Pessoa();
    
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    
    public void save(){
    
        try{
            this.usuario.setCodigo(pessoa);
        facadeLocal.create(usuario);
        }
        catch(Exception ex){
        
        
        
        }
    
    
    
    }
    
    
    
    
    
}
