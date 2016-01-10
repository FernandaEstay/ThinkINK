package facade;
/**
*
* @author fernanda
*/


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Galeria;

@Stateless
public class GaleriaEJBFacade extends AbstractFacade<Galeria> {
	
	 @PersistenceContext(unitName = "thinkINK")
		private EntityManager em;
	    public GaleriaEJBFacade() {
	        super(Galeria.class);
	    }

	    @Override
	    protected EntityManager getEntityManager() {
	        return em;
	    }
}
