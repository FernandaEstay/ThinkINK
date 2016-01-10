package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Trabajo;

@Stateless
public class TrabajoEJBFacade extends AbstractFacade<Trabajo> {
	@PersistenceContext(unitName = "thinkINK")
	private EntityManager em;
    public TrabajoEJBFacade() {
        super(Trabajo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
