/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangalo.cursoprimeavancado.controller;

import com.hangalo.cursoprimeavancado.ejb.MenuFacadeLocal;
import com.hangalo.cursoprimeavancado.model.Menu;
import com.hangalo.cursoprimeavancado.model.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author informatica
 */
@Named
@SessionScoped
public class MenuController implements Serializable{
    @EJB
    private MenuFacadeLocal EJBMenu;
    
    private List<Menu> lista;
    
    private MenuModel model;
    
    
    @PostConstruct
    public void init(){
    this.listarMenus();
    model = new DefaultMenuModel();
    this.definirPermissoes();
    }
    
    public void listarMenus(){
        try {
            lista=EJBMenu.findAll();
        } catch (Exception e) {
        }
    
    }
    
    public void definirPermissoes(){
    User us = (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLogin");
        for (Menu m: lista) {
            if (m.getTipo().equals("S")&& m.getTipoUsuario().equals(us.getTipo())) {
                DefaultSubMenu firstSubMenu = new DefaultSubMenu(m.getNome());
                for(Menu i: lista){
                
                Menu subMenu = i.getSubmenu();
                    if (subMenu != null) {
                        if(subMenu.getCodigo() == m.getCodigo()){
                            DefaultMenuItem item = new DefaultMenuItem(i.getNome());
                            item.setUrl(i.getUrl());
                            firstSubMenu.addElement(item);
                        
                        }
                    }
                }
                model.addElement(firstSubMenu);
            }else{            
                if(m.getSubmenu() == null&& m.getTipoUsuario().equals(us.getTipo())){
                DefaultMenuItem item = new DefaultMenuItem(m.getNome());
                 item.setUrl(m.getUrl());
                 
                model.addElement(item);
                
                }
            
             
            }
        }
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
    
     public void fecharSessao(){
     FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
     
     }
    
     public String mostrarUsuarioLogado(){
     
         User us = (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLogin");
         return us.getUsuario();
     
     }
     
}
