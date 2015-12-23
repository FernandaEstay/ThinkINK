package ejb;

import javax.ejb.Local;

import models.Galeria;
import models.Usuario;

@Local
public interface GaleriaEJBLocal {
	Galeria obtenerGaleriaUsuario(Usuario usuario, String tipo);
}
