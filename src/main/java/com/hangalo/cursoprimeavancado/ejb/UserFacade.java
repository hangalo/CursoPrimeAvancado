/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangalo.cursoprimeavancado.ejb;

import com.hangalo.cursoprimeavancado.model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author informatica
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    @Override
    public User iniciarSessao(User userParam) {
        User user = null;
        String consulta;
        try {
            consulta = "SELECT u FROM User u WHERE u.usuario = ?1 AND u.senha = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, userParam.getUsuario());
            query.setParameter(2, userParam.getSenha());
            List<User> lista = query.getResultList();
            if (!lista.isEmpty()) {
                user = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }

        return user;
    }

}
