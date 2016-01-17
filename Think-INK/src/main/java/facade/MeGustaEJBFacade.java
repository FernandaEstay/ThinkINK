package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.MeGusta;

@Stateless
public class MeGustaEJBFacade extends AbstractFacade<MeGusta> {
	@PersistenceContext(unitName = "thinkINK")
	private EntityManager em;
    public MeGustaEJBFacade() {
        super(MeGusta.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
