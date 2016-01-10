package ejb;

import java.util.List;
import java.util.Collection;

import javax.ejb.Local;

import models.Trabajo;
import models.Usuario;

@Local
public interface TrabajoEJBLocal {

	public void guardarTrabajo(Usuario usuario, Trabajo trabajo);
	public Boolean comprobarTrabajo(Trabajo trabajo);
}
