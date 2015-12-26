package ejb;

import java.util.List;
import java.util.Collection;

import javax.ejb.Local;

import models.Galeria;
import models.Usuario;
import models.Foto;

@Local
public interface GaleriaEJBLocal {
	Galeria obtenerGaleriaUsuario(Usuario usuario, String tipo);
	List<Foto> obtenerGaleria(Galeria galeria);
}