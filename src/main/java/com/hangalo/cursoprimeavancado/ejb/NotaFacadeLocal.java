/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangalo.cursoprimeavancado.ejb;

import com.hangalo.cursoprimeavancado.model.Nota;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author informatica
 */
@Local
public interface NotaFacadeLocal {

    void create(Nota nota);

    void edit(Nota nota);

    void remove(Nota nota);

    Nota find(Object id);

    List<Nota> findAll();

    List<Nota> findRange(int[] range);

    int count();
    Nota iniciarSessao(Nota notaParam);
    
}
