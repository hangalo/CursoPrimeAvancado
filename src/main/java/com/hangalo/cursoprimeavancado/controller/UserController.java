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
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author informatica
 */


@Named
@ViewScoped
public class UserController implements Serializable{
    @EJB
    UserFacadeLocal facadeLocal;
    @Inject
    private User user;
  
    
    @PostConstruct
    public void init(){
  //  user = new User();
  
    
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

 

   
    
    
    public void save(){
    
        try{
           
        facadeLocal.create(user);
        }
        catch(Exception ex){
        
        
        
        }
    
    
    
    }
    
    
    
    
    
}
