package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Foto;

@Stateless
public class FotoEJBFacade extends AbstractFacade<Foto> {
	@PersistenceContext(unitName = "thinkINK")
	private EntityManager em;
    public FotoEJBFacade() {
        super(Foto.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
