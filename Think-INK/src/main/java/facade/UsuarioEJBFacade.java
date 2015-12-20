/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

/**
 *
 * @author fernanda
 */


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Usuario;

@Stateless
public class UsuarioEJBFacade extends AbstractFacade<Usuario>{

        @PersistenceContext(unitName = "thinkINK")
	private EntityManager em;
    public UsuarioEJBFacade() {
        super(Usuario.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
